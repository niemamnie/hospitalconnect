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
@Table(name = "human_resource")
public class HumanResource {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false, unique = true)
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    private HumanResourceType humanType;

    @Enumerated(EnumType.ORDINAL)
    private ResourceStatus status;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private User user;


}
