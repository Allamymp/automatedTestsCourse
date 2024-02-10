package study.automatedtestscourse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.automatedtestscourse.models.Planet;
import study.automatedtestscourse.service.PlanetService;

@RestController
@RequestMapping("/planets")
public class PlanetController {

    @Autowired
    private PlanetService planetService;
    @PostMapping
    public ResponseEntity<Planet> create(@RequestBody Planet planet){
        return  ResponseEntity.status(HttpStatus.CREATED).body(planetService.create(planet));
    }
}