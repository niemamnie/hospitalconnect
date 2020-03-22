package de.hackathon.hospitalconnect.objects.resource;


import com.fasterxml.jackson.annotation.JsonIgnore;
import de.hackathon.hospitalconnect.objects.enums.ResourceStatus;
import de.hackathon.hospitalconnect.objects.hospitals.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class MaterialResource {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(updatable = false, nullable = false, unique = true)
    private Long id;

    @Enumerated(EnumType.ORDINAL)
    private ResourceStatus status;

    @ManyToOne
    private DefaultMaterialResource defaultMaterialResource;

    @ManyToOne
    @JsonIgnore
    private User user;


}
