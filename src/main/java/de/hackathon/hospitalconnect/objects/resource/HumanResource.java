package de.hackathon.hospitalconnect.objects.resource;

import com.fasterxml.jackson.annotation.JsonIgnore;
import de.hackathon.hospitalconnect.objects.enums.ResourceStatus;
import de.hackathon.hospitalconnect.objects.user.User;
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

    @ManyToOne(cascade = CascadeType.ALL)
    private HumanResourceName name;

    @Enumerated(EnumType.ORDINAL)
    private ResourceStatus status;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private User user;


}
