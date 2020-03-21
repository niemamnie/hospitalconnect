package de.hackathon.hospitalconnect.objects.hospitals;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "hospital_location")
@Getter
@Setter
@NoArgsConstructor
public class HospitalLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(updatable = false, nullable = false, unique = true)
    private Long id;

    private Double lat;

    private Double lng;

    private int PLZ;

    private String city;

    @OneToOne
    @JsonIgnore
    private User user;

    /**
     * Use {@link User#setLocation(HospitalLocation)}
     * to assign personal resource
     *
     * @param user
     */
    public void setUser(User user) {
        this.user = user;
    }
}
