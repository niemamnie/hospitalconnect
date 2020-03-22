package de.hackathon.hospitalconnect.rest.resources;

import de.hackathon.hospitalconnect.objects.resource.DefaultResources;
import de.hackathon.hospitalconnect.objects.resource.MaterialResourceName;
import de.hackathon.hospitalconnect.objects.resource.PersonalResourceName;
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
    public ResponseEntity<PersonalResourceName> getDefaultPersonalResource(@PathVariable Long id) {
        return new ResponseEntity<>(drController.getDefaultPersonalResource(id), HttpStatus.OK);
    }

    @GetMapping("/material/{id}")
    public ResponseEntity<MaterialResourceName> getDefaultMaterialResource(@PathVariable Long id) {
        return new ResponseEntity<>(drController.getDefaultMaterialResource(id), HttpStatus.OK);
    }

    @PutMapping("/personal")
    public ResponseEntity createNewDefaultPersonResource(@RequestBody PersonalResourceName personalResourceName) {
        drController.saveDefaultPersonalResource(personalResourceName);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/material")
    public ResponseEntity createNewDefaultMaterialResource(@RequestBody MaterialResourceName MaterialResourceName) {
        drController.saveDefaultMaterialResource(MaterialResourceName);
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
