package de.hackathon.hospitalconnect.rest.resources;


import de.hackathon.hospitalconnect.exceptions.InternException;
import de.hackathon.hospitalconnect.model.resource.DefaultResources;
import de.hackathon.hospitalconnect.model.resource.HumanResourceType;
import de.hackathon.hospitalconnect.model.resource.MaterialResourceType;
import de.hackathon.hospitalconnect.model.resource.repositories.HumanResourceTypeRepository;
import de.hackathon.hospitalconnect.model.resource.repositories.MaterialResourceTypeRepository;
import de.hackathon.hospitalconnect.service.CopyService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ResourceTypeService {


    private final MaterialResourceTypeRepository materialResourceTypeRepository;

    private final HumanResourceTypeRepository humanResourceTypeRepository;

    private final CopyService copyService;

    public ResourceTypeService(MaterialResourceTypeRepository materialResourceTypeRepository, HumanResourceTypeRepository humanResourceTypeRepository, CopyService copyService) {
        this.materialResourceTypeRepository = materialResourceTypeRepository;
        this.humanResourceTypeRepository = humanResourceTypeRepository;
        this.copyService = copyService;
    }

    public DefaultResources getAllDefaultResources() {
        return new DefaultResources(
                humanResourceTypeRepository.findAll(),
                materialResourceTypeRepository.findAll()
        );
    }

    public HumanResourceType geHumanTypeResource(Long id) {
        return humanResourceTypeRepository.getOne(id);
    }

    public void saveHumanTypeResource(HumanResourceType humanResourceType) {
        humanResourceTypeRepository.saveAndFlush(humanResourceType);
    }

    public void deleteHuamnResourceType(Long id) {
        humanResourceTypeRepository.deleteById(id);
    }

    public void patchHumanResourceType(Long id, HumanResourceType humanResource) {
        Optional<HumanResourceType> savedHumanResource = humanResourceTypeRepository.getById(id);
        if (savedHumanResource.isPresent()) {
            copyService.map(humanResource, savedHumanResource.get());
            humanResourceTypeRepository.saveAndFlush(savedHumanResource.get());
        } else {
            throw new InternException("Human resource could not be found", HttpStatus.NOT_FOUND);
        }
    }

    public MaterialResourceType getMaterialTypeResource(Long id) {
        return materialResourceTypeRepository.getOne(id);
    }

    public void saveDefaultMaterialResource(MaterialResourceType MaterialResourceType) {
        materialResourceTypeRepository.saveAndFlush(MaterialResourceType);
    }

    public void deleteMaterialResourceType(Long id) {
        materialResourceTypeRepository.deleteById(id);
    }

}
