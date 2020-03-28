package de.hackathon.hospitalconnect.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import de.hackathon.hospitalconnect.converters.CustomAttrubuteConverter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "contact")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(updatable = false, nullable = false, unique = true)
    private Long id;
    @Convert(converter = CustomAttrubuteConverter.class)
    private String name;
    @Convert(converter = CustomAttrubuteConverter.class)
    private String surname;
    @Convert(converter = CustomAttrubuteConverter.class)
    private String tel;

    @OneToOne
    @JsonIgnore
    private User user;
}
