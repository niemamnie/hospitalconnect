package de.hackathon.hospitalconnect.objects.resource;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
public class DefaultPersonalResource {
    @Id
    @Column(nullable = false, unique = true, updatable = false)
    private String name;

    @OneToMany(cascade = {CascadeType.REMOVE, CascadeType.REFRESH}, mappedBy = "defaultPersonalResource")
    private List<PersonalResource> personalResources;
}
