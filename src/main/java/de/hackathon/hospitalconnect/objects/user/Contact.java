package de.hackathon.hospitalconnect.objects.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    private String name;

    private String surname;

    private String tel;

    @OneToOne
    @JsonIgnore
    private User user;
}
