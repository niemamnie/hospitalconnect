package de.hackathon.hospitalconnect.rest.resources;

import de.hackathon.hospitalconnect.model.resource.MaterialResource;
import de.hackathon.hospitalconnect.model.resource.repositories.MaterialResourceRepository;
import de.hackathon.hospitalconnect.rest.exceptions.InternException;
import de.hackathon.hospitalconnect.service.CopyService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class MaterialResourcesService {

    private final CopyService copyService;
    private final MaterialResourceRepository materialResourceRepository;

    public MaterialResourcesService(CopyService copyService, MaterialResourceRepository materialResourceRepository) {
        this.copyService = copyService;
        this.materialResourceRepository = materialResourceRepository;
    }

    public MaterialResource getMaterialResource(Long id) {
        return materialResourceRepository.getById(id).orElseThrow(
                () -> new InternException("not Found", HttpStatus.NOT_FOUND));
    }

    public void updateMaterialResource(Long id, MaterialResource materialResource) {
        MaterialResource savedResource = materialResourceRepository.getById(id).orElseThrow(
                () -> new InternException("not Found", HttpStatus.NOT_FOUND));
        copyService.map(materialResource, savedResource);
        materialResourceRepository.saveAndFlush(savedResource);
    }
}
