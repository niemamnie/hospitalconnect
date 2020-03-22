package de.hackathon.hospitalconnect.rest.resources;


import de.hackathon.hospitalconnect.objects.resource.DefaultResources;
import de.hackathon.hospitalconnect.objects.resource.MaterialResourceName;
import de.hackathon.hospitalconnect.objects.resource.PersonalResourceName;
import de.hackathon.hospitalconnect.objects.resource.repositories.DefaultMaterialResourceRepository;
import de.hackathon.hospitalconnect.objects.resource.repositories.DefaultPersonalResourceRepository;
import org.springframework.stereotype.Component;

@Component
public class DefaultResourcesController {


    private final DefaultMaterialResourceRepository defaultMaterialResourceRepository;

    private final DefaultPersonalResourceRepository defaultPersonalResourceRepository;

    public DefaultResourcesController(DefaultMaterialResourceRepository defaultMaterialResourceRepository, DefaultPersonalResourceRepository defaultPersonalResourceRepository) {
        this.defaultMaterialResourceRepository = defaultMaterialResourceRepository;
        this.defaultPersonalResourceRepository = defaultPersonalResourceRepository;
    }

    public DefaultResources getAllDefaultResources() {
        return new DefaultResources(
                defaultPersonalResourceRepository.findAll(),
                defaultMaterialResourceRepository.findAll()
        );
    }

    public void saveDefaultPersonalResource(PersonalResourceName personalResourceName) {
        defaultPersonalResourceRepository.saveAndFlush(personalResourceName);
    }

    public void saveDefaultMaterialResource(MaterialResourceName MaterialResourceName) {
        defaultMaterialResourceRepository.saveAndFlush(MaterialResourceName);
    }

    public MaterialResourceName getDefaultMaterialResource(Long id) {
        return defaultMaterialResourceRepository.getOne(id);
    }

    public PersonalResourceName getDefaultPersonalResource(Long id) {
        return defaultPersonalResourceRepository.getOne(id);
    }
}
