package de.hackathon.hospitalconnect.rest.resources;

import de.hackathon.hospitalconnect.exceptions.InternException;
import de.hackathon.hospitalconnect.model.resource.HumanResource;
import de.hackathon.hospitalconnect.model.resource.repositories.HumanResourceRepository;
import de.hackathon.hospitalconnect.service.CopyService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class HumanResourceService {
    private final HumanResourceRepository humanResourceRepository;

    private final CopyService copyService;

    public HumanResourceService(HumanResourceRepository humanResourceRepository, CopyService copyService) {
        this.humanResourceRepository = humanResourceRepository;
        this.copyService = copyService;
    }

    public HumanResource getHumanResourceById(Long id) {
        return this.humanResourceRepository.getById(id)
                .orElseThrow(() -> new InternException("Not found", HttpStatus.NOT_FOUND));
    }

    public void updateHumanResource(Long id, HumanResource humanResource) {
        HumanResource savedResource = humanResourceRepository.getById(id).orElseThrow(
                () -> new InternException("Not found", HttpStatus.NOT_FOUND));
        copyService.map(humanResource, savedResource);
        humanResourceRepository.saveAndFlush(savedResource);
    }
}
