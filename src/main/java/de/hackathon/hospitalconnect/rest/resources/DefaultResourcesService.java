package de.hackathon.hospitalconnect.rest.resources;


import de.hackathon.hospitalconnect.objects.resource.DefaultResources;
import de.hackathon.hospitalconnect.objects.resource.HumanResourceName;
import de.hackathon.hospitalconnect.objects.resource.MaterialResourceName;
import de.hackathon.hospitalconnect.objects.resource.repositories.DefaultMaterialResourceRepository;
import de.hackathon.hospitalconnect.objects.resource.repositories.HumanResourceNameRepository;
import de.hackathon.hospitalconnect.rest.exceptions.InternException;
import de.hackathon.hospitalconnect.service.CopyService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DefaultResourcesService {


    private final DefaultMaterialResourceRepository defaultMaterialResourceRepository;

    private final HumanResourceNameRepository humanResourceNameRepository;

    private final CopyService copyService;

    public DefaultResourcesService(DefaultMaterialResourceRepository defaultMaterialResourceRepository, HumanResourceNameRepository humanResourceNameRepository, CopyService copyService) {
        this.defaultMaterialResourceRepository = defaultMaterialResourceRepository;
        this.humanResourceNameRepository = humanResourceNameRepository;
        this.copyService = copyService;
    }

    public DefaultResources getAllDefaultResources() {
        return new DefaultResources(
                humanResourceNameRepository.findAll(),
                defaultMaterialResourceRepository.findAll()
        );
    }

    public HumanResourceName getDefaultPersonalResource(Long id) {
        return humanResourceNameRepository.getOne(id);
    }

    public void saveDefaultPersonalResource(HumanResourceName humanResourceName) {
        humanResourceNameRepository.saveAndFlush(humanResourceName);
    }

    public void patchDefaultHumanResource(Long id, HumanResourceName humanResource) {
        Optional<HumanResourceName> savedHumanResource = humanResourceNameRepository.getById(id);
        if (savedHumanResource.isPresent()) {
            copyService.map(humanResource, savedHumanResource.get());
            humanResourceNameRepository.saveAndFlush(savedHumanResource.get());
        } else {
            throw new InternException("Human resource could not be found", HttpStatus.NOT_FOUND);
        }
    }

    public MaterialResourceName getDefaultMaterialResource(Long id) {
        return defaultMaterialResourceRepository.getOne(id);
    }

    public void saveDefaultMaterialResource(MaterialResourceName MaterialResourceName) {
        defaultMaterialResourceRepository.saveAndFlush(MaterialResourceName);
    }

}
