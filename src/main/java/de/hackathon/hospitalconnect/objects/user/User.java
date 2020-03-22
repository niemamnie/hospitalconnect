package de.hackathon.hospitalconnect.objects.user;


import de.hackathon.hospitalconnect.objects.enums.UserType;
import de.hackathon.hospitalconnect.objects.resource.MaterialResource;
import de.hackathon.hospitalconnect.objects.resource.PersonalResource;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(updatable = false, nullable = false, unique = true)
    private Long id;

    @Enumerated(EnumType.ORDINAL)
    private UserType type;

    @Column
    private String name;


    private String verband;

    @OneToOne(mappedBy = "user", cascade = {CascadeType.REMOVE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    private Credentials credentials;

    private String tel;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Contact contact;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Address address;

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.REMOVE}, mappedBy = "user", fetch = FetchType.LAZY)
    private List<PersonalResource> personal_resources;

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.REMOVE}, mappedBy = "user", fetch = FetchType.LAZY)
    private List<MaterialResource> material_resources;

    public void setAddress(Address address) {
        address.setUser(this);
        this.address = address;
    }

    public void setCredentials(Credentials credentials) {
        credentials.setUser(this);
        this.credentials = credentials;
    }

    public void setContact(Contact contact) {
        contact.setUser(this);
        this.contact = contact;
    }

    public void setPersonal_resources(List<PersonalResource> personalResources) {
        for (PersonalResource resource : personalResources) {
            resource.setUser(this);
        }
        this.personal_resources = personalResources;
    }

    public void setMaterial_resources(List<MaterialResource> materialResources) {
        for (MaterialResource resource : materialResources) {
            resource.setUser(this);
        }
        this.material_resources = materialResources;
    }

}
