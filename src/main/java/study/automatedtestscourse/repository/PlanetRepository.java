package study.automatedtestscourse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.automatedtestscourse.models.Planet;

import java.util.List;
import java.util.Optional;

public interface PlanetRepository extends JpaRepository<Planet, Long> {
    Optional<Planet> findByName(String name);

    List<Planet> findAllByClimateAndTerrain(String climate, String terrain);
}
