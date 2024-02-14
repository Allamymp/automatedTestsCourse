package study.automatedtestscourse.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import study.automatedtestscourse.models.Planet;
import study.automatedtestscourse.service.PlanetService;

import java.util.Optional;

@RestController
@RequestMapping("/planets")
public class PlanetController {

    @Autowired
    private PlanetService planetService;

    @PostMapping
    public ResponseEntity<Planet> create(@RequestBody  @Valid Planet planet) {
        return ResponseEntity.status(HttpStatus.CREATED).body(planetService.create(planet));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Planet> getById(@PathVariable Long id) {
        Optional<Planet> planetOptional = planetService.findById(id);
        return planetOptional.map(planet -> ResponseEntity.status(HttpStatus.OK).body(planet))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Planet> getByName(@PathVariable String name) {
        Optional<Planet> planetOptional = planetService.findByName(name);
        return planetOptional.map(planet -> ResponseEntity.status(HttpStatus.OK).body(planet))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping
    public ResponseEntity<Planet> getByFilters(@RequestParam String climate, String terrain) {
        Optional<Planet> planetOptional = planetService.findByFilters(climate, terrain);
        return planetOptional.map(planet -> ResponseEntity.status(HttpStatus.OK).body(planet))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePlanet(@PathVariable Long id) {
        planetService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
