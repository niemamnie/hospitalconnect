package de.hackathon.hospitalconnect.rest.resources.mapping;

import de.hackathon.hospitalconnect.objects.resource.DefaultMaterialResource;
import de.hackathon.hospitalconnect.objects.resource.DefaultPersonalResource;
import de.hackathon.hospitalconnect.objects.resource.DefaultResources;
import de.hackathon.hospitalconnect.rest.resources.DefaultResourcesController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
@RequestMapping("/settings/resource")
public class DefaultResourcesMapping {

    private final DefaultResourcesController drController;

    public DefaultResourcesMapping(DefaultResourcesController drController) {
        this.drController = drController;
    }

    @GetMapping
    public ResponseEntity<DefaultResources> getDefaultResources() {
        return new ResponseEntity<>(drController.getAllDefaultResources(), HttpStatus.OK);
    }

    @GetMapping("/personal/{id}")
    public ResponseEntity<DefaultPersonalResource> getDefaultPersonalResource(@PathVariable Long id) {
        return new ResponseEntity<>(drController.getDefaultPersonalResource(id), HttpStatus.OK);
    }

    @GetMapping("/material/{id}")
    public ResponseEntity<DefaultMaterialResource> getDefaultMaterialResource(@PathVariable Long id) {
        return new ResponseEntity<>(drController.getDefaultMaterialResource(id), HttpStatus.OK);
    }

    @PutMapping("/personal")
    public ResponseEntity createNewDefaultPersonResource(@RequestBody DefaultPersonalResource defaultPersonalResource) {
        drController.saveDefaultPersonalResource(defaultPersonalResource);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/material")
    public ResponseEntity createNewDefaultMaterialResource(@RequestBody DefaultMaterialResource defaultMaterialResource) {
        drController.saveDefaultMaterialResource(defaultMaterialResource);
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
