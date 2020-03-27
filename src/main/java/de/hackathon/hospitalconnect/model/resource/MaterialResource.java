package de.hackathon.hospitalconnect.model.resource;


import com.fasterxml.jackson.annotation.JsonIgnore;
import de.hackathon.hospitalconnect.model.enums.ResourceStatus;
import de.hackathon.hospitalconnect.model.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "material_resource")
public class MaterialResource {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(updatable = false, nullable = false, unique = true)
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private MaterialResourceType materialType;

    @Enumerated(EnumType.ORDINAL)
    private ResourceStatus status;

    @ManyToOne
    @JsonIgnore
    private User user;

    public void setMaterialType(MaterialResourceType material_resource_type) {
        this.materialType = material_resource_type;
    }
}
