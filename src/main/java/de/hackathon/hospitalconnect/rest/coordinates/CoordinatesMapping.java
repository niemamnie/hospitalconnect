package de.hackathon.hospitalconnect.rest.coordinates;


import de.hackathon.hospitalconnect.objects.user.Coordinates;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
@RequestMapping
public class CoordinatesMapping {

    private final CoordinatesController coordinatesController;


    public CoordinatesMapping(CoordinatesController coordinatesController) {
        this.coordinatesController = coordinatesController;
    }

    @GetMapping("/get/coordinates/{id}")
    public ResponseEntity<Coordinates> getCoordinates(@PathVariable Long id) {
        return new ResponseEntity<>(coordinatesController.getCoordinates(id), HttpStatus.OK);
    }

    @PatchMapping("patch/coordinates/{id}")
    public ResponseEntity patchCoordinates(@PathVariable Long id, @RequestBody Coordinates coordinates) {
        coordinatesController.updateCoordinates(id, coordinates);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
