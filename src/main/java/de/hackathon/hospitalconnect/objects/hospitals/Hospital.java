package de.hackathon.hospitalconnect.objects.hospitals;


import de.hackathon.hospitalconnect.objects.resources.MaterialResource;
import de.hackathon.hospitalconnect.objects.resources.PersonalResource;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@Table(name = "hospital")
public class Hospital {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(updatable = false, nullable = false, unique = true)
    private UUID id;

    @Column
    private String name;

    @OneToOne(mappedBy = "hospital")
    private HospitalLocation location;


    @OneToOne(mappedBy = "hospital")
    private PersonalResource personalResource;

    @OneToOne(mappedBy = "hospital")
    private MaterialResource materialResource;

    public void setPersonalResource(PersonalResource resource) {
        resource.setHospital(this);
        personalResource = resource;
    }

    public void setLocation(HospitalLocation location) {
        location.setHospital(this);
        this.location = location;
    }

    public void setMaterialResource(MaterialResource materialResource) {
        materialResource.setHospital(this);
        this.materialResource = materialResource;
    }
}
