package de.hackathon.hospitalconnect.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import de.hackathon.hospitalconnect.converters.CustomAttrubuteConverter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "credentials")
public class Credentials {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(updatable = false, nullable = false, unique = true)
    private Long id;

    @Column(unique = true)
    @Convert(converter = CustomAttrubuteConverter.class)
    private String email;


    private String password;

    @OneToOne
    @JsonIgnore
    private User user;
}
