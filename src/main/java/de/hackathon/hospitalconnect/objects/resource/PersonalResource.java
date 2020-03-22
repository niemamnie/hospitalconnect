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
@Table(name = "personal_resource")
public class PersonalResource {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(updatable = false, nullable = false, unique = true)
    private Long id;

    private String name;

    @Enumerated(EnumType.ORDINAL)
    private ResourceStatus status;

    @ManyToOne
    @JsonIgnore
    private User user;


}
