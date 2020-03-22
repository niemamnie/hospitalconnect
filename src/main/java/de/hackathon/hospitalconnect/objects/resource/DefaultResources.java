package de.hackathon.hospitalconnect.objects.resource;

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
    private List<DefaultPersonalResource> defaultPersonalResources;
    private List<DefaultMaterialResource> defaultMaterialResources;
}
