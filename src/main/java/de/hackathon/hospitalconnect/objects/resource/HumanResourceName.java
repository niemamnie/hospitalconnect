package de.hackathon.hospitalconnect.objects.resource;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
public class HumanResourceName {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false, unique = true, updatable = false)
    private Long id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "name", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<HumanResource> resource_list;

}
