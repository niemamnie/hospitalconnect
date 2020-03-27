package de.hackathon.hospitalconnect.model.resource;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
public class HumanResourceType {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false, unique = true, updatable = false)
    private Long id;

    @Column(unique = true)
    private String humanName;


}
