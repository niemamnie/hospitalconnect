package de.hackathon.hospitalconnect.rest.resources;

import de.hackathon.hospitalconnect.model.resource.DefaultResources;
import de.hackathon.hospitalconnect.model.resource.HumanResourceType;
import de.hackathon.hospitalconnect.model.resource.MaterialResourceType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/settings/resource")
public class ResourceTypeController {

    private final ResourceTypeService drController;

    public ResourceTypeController(ResourceTypeService drController) {
        this.drController = drController;
    }

    @GetMapping
    public ResponseEntity<DefaultResources> getDefaultResources() {
        return new ResponseEntity<>(drController.getAllDefaultResources(), HttpStatus.OK);
    }

    @GetMapping("get/human/{id}")
    public ResponseEntity<HumanResourceType> getDefaultPersonalResource(@PathVariable Long id) {
        return new ResponseEntity<>(drController.geHumanTypeResource(id), HttpStatus.OK);
    }

    @PostMapping("post/human")
    public ResponseEntity createNewDefaultPersonResource(@RequestBody HumanResourceType humanResourceType) {
        drController.saveHumanTypeResource(humanResourceType);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @DeleteMapping("delete/human/{id}")
    public void deleteHumanType(@PathVariable Long id) {
        drController.deleteHuamnResourceType(id);
    }

    @PatchMapping("/patch/human/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void patchDefaultHumanResource(@PathVariable Long id, @RequestBody HumanResourceType humanResource) {
        drController.patchHumanResourceType(id, humanResource);
    }

    @GetMapping("get/material/{id}")
    public ResponseEntity<MaterialResourceType> getDefaultMaterialResource(@PathVariable Long id) {
        return new ResponseEntity<>(drController.getMaterialTypeResource(id), HttpStatus.OK);
    }

    @PostMapping("post/material")
    public ResponseEntity createNewDefaultMaterialResource(@RequestBody MaterialResourceType MaterialResourceType) {
        drController.saveDefaultMaterialResource(MaterialResourceType);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/material/{id}")
    public void deleteMaterialResourceType(@PathVariable Long id) {
        drController.deleteMaterialResourceType(id);
    }
}
