package de.hackathon.hospitalconnect.objects.resources;

import de.hackathon.hospitalconnect.objects.enums.NeedStage;
import de.hackathon.hospitalconnect.objects.hospitals.Hospital;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@Table(name = "material_resource")
public class MaterialResource {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(updatable = false, nullable = false, unique = true)
    private UUID id;

    @Enumerated(value = EnumType.STRING)
    private NeedStage needStage;

    @OneToOne
    private Hospital hospital;

    /**
     * Use {@link Hospital#setPersonalResource(PersonalResource)}
     * to assign personal resource
     *
     * @param hospital
     */
    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

}
