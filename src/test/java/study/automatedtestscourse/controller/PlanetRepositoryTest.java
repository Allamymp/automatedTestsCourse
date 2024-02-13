package study.automatedtestscourse.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import study.automatedtestscourse.models.Planet;
import study.automatedtestscourse.repository.PlanetRepository;

import static study.automatedtestscourse.common.PlanetConstants.PLANET;
import static org.assertj.core.api.Assertions.assertThat;


//@DataJpaTest configura automaticamente um banco em memória
@DataJpaTest
public class PlanetRepositoryTest {
    @Autowired
    private PlanetRepository planetRepository;
    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void createPlanet_WithValidData_ReturnsPlanet() {
        //Arrange
        Planet planet = planetRepository.save(PLANET);
        //Act
       Planet sut =  testEntityManager.find(Planet.class,planet.getPlanetId());
       //Assert
        assertThat(sut).isNotNull();
        assertThat(planet.getName()).isEqualTo(sut.getName());
        assertThat(planet.getClimate()).isEqualTo(sut.getClimate());
        assertThat(planet.getTerrain()).isEqualTo(sut.getTerrain());
    }
}
