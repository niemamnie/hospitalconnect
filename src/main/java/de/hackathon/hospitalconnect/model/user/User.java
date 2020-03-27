package de.hackathon.hospitalconnect.model.user;


import com.fasterxml.jackson.annotation.JsonProperty;
import de.hackathon.hospitalconnect.model.enums.UserType;
import de.hackathon.hospitalconnect.model.resource.HumanResource;
import de.hackathon.hospitalconnect.model.resource.MaterialResource;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
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

    @ElementCollection(fetch = FetchType.LAZY, targetClass = Role.class)
    @Enumerated(EnumType.ORDINAL)
    @JsonProperty(value = "roles", access = JsonProperty.Access.WRITE_ONLY)
    private List<Role> roles = new ArrayList<>();


    private String verband;


    private String tel;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Contact contact;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Address address;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.LAZY)
    private List<HumanResource> humanResources;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.LAZY)
    private List<MaterialResource> materialResources;

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

    public void setHumanResources(List<HumanResource> humanResources) {
        for (HumanResource resource : humanResources) {
            resource.setUser(this);
        }
        this.humanResources = humanResources;
    }

    public void setMaterialResources(List<MaterialResource> materialResources) {
        for (MaterialResource resource : materialResources) {
            resource.setUser(this);
        }
        this.materialResources = materialResources;
    }

    public void addRole(Role role) {
        roles.add(role);
    }
}
