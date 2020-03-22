package de.hackathon.hospitalconnect.objects.resource;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class MaterialResourceName {
    @Id
    @Column(nullable = false, unique = true, updatable = false)
    private String name;

}
