package de.hackathon.hospitalconnect.objects.hospitals;

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

    private String email;

    private String password;

    @OneToOne
    private User user;
}
