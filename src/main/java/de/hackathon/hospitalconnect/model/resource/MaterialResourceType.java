package de.hackathon.hospitalconnect.model.resource;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class MaterialResourceType {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false, unique = true, updatable = false)
    private Long id;

    @Column(unique = true)
    private String materialName;


    public MaterialResourceType(String materialName) {
        this.materialName = materialName;
    }
}
