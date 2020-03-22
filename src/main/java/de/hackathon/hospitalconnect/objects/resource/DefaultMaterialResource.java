package de.hackathon.hospitalconnect.objects.resource;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class DefaultMaterialResource {
    @Id
    @Column(nullable = false, unique = true, updatable = false)
    private String name;

    @OneToMany(cascade = {CascadeType.REMOVE, CascadeType.REFRESH}, mappedBy = "defaultMaterialResource")
    private List<MaterialResource> materialResources;
}
