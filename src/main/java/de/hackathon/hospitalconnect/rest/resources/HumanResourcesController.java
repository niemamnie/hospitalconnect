package de.hackathon.hospitalconnect.rest.resources;

import de.hackathon.hospitalconnect.model.resource.HumanResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController

public class HumanResourcesController {

    private final HumanResourceService humanResourceService;


    public HumanResourcesController(HumanResourceService humanResourceService) {
        this.humanResourceService = humanResourceService;
    }

    @GetMapping("/get/human/resource/{id}")
    public ResponseEntity<HumanResource> getHumanResource(@PathVariable Long id) {
        return new ResponseEntity<>(humanResourceService.getHumanResourceById(id), HttpStatus.OK);
    }

    @PostMapping("/post/human/resource/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateHumanResource(@PathVariable Long id, @RequestBody HumanResource humanResource) {
        humanResourceService.updateHumanResource(id, humanResource);
    }
}
