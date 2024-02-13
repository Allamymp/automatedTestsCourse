package study.automatedtestscourse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import study.automatedtestscourse.models.Planet;
import study.automatedtestscourse.repository.PlanetRepository;

import java.util.Optional;

@Service
public class PlanetService {


    private final PlanetRepository planetRepository;

    public PlanetService(PlanetRepository planetRepository) {
        this.planetRepository = planetRepository;
    }

    public  Planet create(Planet planet){
        return planetRepository.save(planet);
    }

    public Optional<Planet> findById(Long id) {
        return planetRepository.findById(id);
    }

    public Optional<Planet> findByName(String name) {
        return planetRepository.findByName(name);
    }

    public Optional<Planet> findByFilters(String climate, String terrain) {
        return planetRepository.findByClimateAndTerrain(climate,terrain);
    }

    public void deleteById(Long id) {
        planetRepository.deleteById(id);
    }
}
