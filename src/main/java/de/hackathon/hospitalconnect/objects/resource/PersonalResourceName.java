package de.hackathon.hospitalconnect.objects.resource;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Setter
@Getter
public class PersonalResourceName {
    @Id
    @Column(nullable = false, unique = true, updatable = false)
    private String name;


}
