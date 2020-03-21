package de.hackathon.hospitalconnect.objects.hospitals;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "hospital_location")
@Data
public class HospitalLocation {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(updatable = false, nullable = false, unique = true)
    private UUID id;

    private Double lat;

    private Double lng;

    @OneToOne
    private Hospital hospital;

    /**
     * Use {@link Hospital#setLocation(HospitalLocation)}
     * to assign personal resource
     *
     * @param hospital
     */
    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }
}
