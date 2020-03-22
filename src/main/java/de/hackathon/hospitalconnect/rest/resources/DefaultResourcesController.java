package de.hackathon.hospitalconnect.rest.resources;


import de.hackathon.hospitalconnect.objects.resource.DefaultMaterialResource;
import de.hackathon.hospitalconnect.objects.resource.DefaultPersonalResource;
import de.hackathon.hospitalconnect.objects.resource.DefaultResources;
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

    public void saveDefaultPersonalResource(DefaultPersonalResource defaultPersonalResource) {
        defaultPersonalResourceRepository.saveAndFlush(defaultPersonalResource);
    }

    public void saveDefaultMaterialResource(DefaultMaterialResource defaultMaterialResource) {
        defaultMaterialResourceRepository.saveAndFlush(defaultMaterialResource);
    }

    public DefaultMaterialResource getDefaultMaterialResource(Long id) {
        return defaultMaterialResourceRepository.getOne(id);
    }

    public DefaultPersonalResource getDefaultPersonalResource(Long id) {
        return defaultPersonalResourceRepository.getOne(id);
    }
}
