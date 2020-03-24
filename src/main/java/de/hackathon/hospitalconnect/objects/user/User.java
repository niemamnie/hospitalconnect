package de.hackathon.hospitalconnect.objects.user;


import com.fasterxml.jackson.annotation.JsonProperty;
import de.hackathon.hospitalconnect.objects.enums.UserType;
import de.hackathon.hospitalconnect.objects.resource.HumanResource;
import de.hackathon.hospitalconnect.objects.resource.MaterialResource;
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
    @Column(nullable = false, unique = true)
    private Long id;

    @Enumerated(EnumType.ORDINAL)
    private UserType type;

    @Column
    private String name;


    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonProperty(value = "credentials", access = JsonProperty.Access.WRITE_ONLY)
    private Credentials credentials;


    private String verband;


    private String tel;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Contact contact;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Address address;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.LAZY)
    private List<HumanResource> human_resources;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.LAZY)
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

    public void setHuman_resources(List<HumanResource> humanResources) {
        for (HumanResource resource : humanResources) {
            resource.setUser(this);
        }
        this.human_resources = humanResources;
    }

    public void setMaterial_resources(List<MaterialResource> materialResources) {
        for (MaterialResource resource : materialResources) {
            resource.setUser(this);
        }
        this.material_resources = materialResources;
    }

}
