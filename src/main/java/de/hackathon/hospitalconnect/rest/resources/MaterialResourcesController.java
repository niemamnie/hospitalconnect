package de.hackathon.hospitalconnect.rest.resources;

import de.hackathon.hospitalconnect.model.resource.MaterialResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MaterialResourcesController {

    private final MaterialResourcesService materialResourcesService;

    public MaterialResourcesController(MaterialResourcesService materialResourcesService) {
        this.materialResourcesService = materialResourcesService;
    }

    @GetMapping("/get/material/resource/{id}")
    public ResponseEntity<MaterialResource> getMaterialResource(@PathVariable Long id) {
        return new ResponseEntity<>(materialResourcesService.getMaterialResource(id), HttpStatus.OK);
    }

    @PostMapping("/post/material/resource/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateMaterialResource(@PathVariable Long id, @RequestBody MaterialResource materialResource) {
        materialResourcesService.updateMaterialResource(id, materialResource);
    }
}
