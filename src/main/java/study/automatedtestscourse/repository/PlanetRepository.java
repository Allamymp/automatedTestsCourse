package study.automatedtestscourse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.automatedtestscourse.models.Planet;

public interface PlanetRepository extends JpaRepository<Planet, Long> {
}
