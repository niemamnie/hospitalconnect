package de.hackathon.hospitalconnect.objects.hospitals;


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

    private String forename;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Credentials credentials;

    private String phoneNumber;


    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private HospitalLocation location;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<PersonalResource> personalResources;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<MaterialResource> materialResources;

    public void setLocation(HospitalLocation location) {
        location.setUser(this);
        this.location = location;
    }

    public void setCredentials(Credentials credentials) {
        credentials.setUser(this);
        this.credentials = credentials;
    }

}
