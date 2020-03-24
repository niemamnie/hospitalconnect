package de.hackathon.hospitalconnect.rest.resources;

import de.hackathon.hospitalconnect.objects.resource.DefaultResources;
import de.hackathon.hospitalconnect.objects.resource.HumanResourceName;
import de.hackathon.hospitalconnect.objects.resource.MaterialResourceName;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/settings/resource")
public class DefaultResourcesController {

    private final DefaultResourcesService drController;

    public DefaultResourcesController(DefaultResourcesService drController) {
        this.drController = drController;
    }

    @GetMapping
    public ResponseEntity<DefaultResources> getDefaultResources() {
        return new ResponseEntity<>(drController.getAllDefaultResources(), HttpStatus.OK);
    }

    @GetMapping("get/human/{id}")
    public ResponseEntity<HumanResourceName> getDefaultPersonalResource(@PathVariable Long id) {
        return new ResponseEntity<>(drController.getDefaultPersonalResource(id), HttpStatus.OK);
    }

    @PostMapping("post/human")
    public ResponseEntity createNewDefaultPersonResource(@RequestBody HumanResourceName humanResourceName) {
        drController.saveDefaultPersonalResource(humanResourceName);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PatchMapping("/patch/human/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void patchDefaultHumanResource(@PathVariable Long id, @RequestBody HumanResourceName humanResource) {
        drController.patchDefaultHumanResource(id, humanResource);
    }

    @GetMapping("get/material/{id}")
    public ResponseEntity<MaterialResourceName> getDefaultMaterialResource(@PathVariable Long id) {
        return new ResponseEntity<>(drController.getDefaultMaterialResource(id), HttpStatus.OK);
    }

    @PostMapping("post/material")
    public ResponseEntity createNewDefaultMaterialResource(@RequestBody MaterialResourceName MaterialResourceName) {
        drController.saveDefaultMaterialResource(MaterialResourceName);
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
