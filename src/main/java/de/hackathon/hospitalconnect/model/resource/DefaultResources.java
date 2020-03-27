package de.hackathon.hospitalconnect.model.resource;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DefaultResources {
    private List<HumanResourceType> humanResourceTypes;
    private List<MaterialResourceType> materialResourceTypes;
}
