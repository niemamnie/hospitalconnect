package de.hackathon.hospitalconnect.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import de.hackathon.hospitalconnect.converters.CustomAttrubuteConverter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "hospital_location")
@Getter
@Setter
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(updatable = false, nullable = false, unique = true)
    private Long id;

    @Convert(converter = CustomAttrubuteConverter.class)
    private String postalcode;
    @Convert(converter = CustomAttrubuteConverter.class)
    private String city;
    @Convert(converter = CustomAttrubuteConverter.class)
    private String street;

    @OneToOne(mappedBy = "address", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Coordinates coordinates;

    @OneToOne
    @JsonIgnore
    private User user;

    /**
     * Use {@link User#setAddress(Address)}
     * to assign personal resource
     *
     * @param user
     */
    public void setUser(User user) {
        this.user = user;
    }

    public void setCoordinates(Coordinates coordinates) {
        coordinates.setAddress(this);
        this.coordinates = coordinates;
    }
}
