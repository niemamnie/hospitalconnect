package de.hackathon.hospitalconnect.rest.coordinates;


import de.hackathon.hospitalconnect.objects.user.Coordinates;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CoordinatesController {

    private final CoordinatesService coordinatesService;


    public CoordinatesController(CoordinatesService coordinatesService) {
        this.coordinatesService = coordinatesService;
    }

    @GetMapping("/get/coordinates/{id}")
    public ResponseEntity<Coordinates> getCoordinates(@PathVariable Long id) {
        return new ResponseEntity<>(coordinatesService.getCoordinates(id), HttpStatus.OK);
    }

    @PatchMapping("patch/coordinates/{id}")
    public ResponseEntity patchCoordinates(@PathVariable Long id, @RequestBody Coordinates coordinates) {
        coordinatesService.updateCoordinates(id, coordinates);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
