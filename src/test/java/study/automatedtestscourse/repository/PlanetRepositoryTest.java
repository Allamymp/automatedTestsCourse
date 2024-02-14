package study.automatedtestscourse.repository;

import org.h2.table.Plan;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import study.automatedtestscourse.models.Planet;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.when;
import static study.automatedtestscourse.common.PlanetConstants.*;


//@DataJpaTest configura automaticamente um banco em memória
@DataJpaTest
public class PlanetRepositoryTest {
    @Autowired
    private PlanetRepository planetRepository;
    @Autowired
    private TestEntityManager testEntityManager;

    @AfterEach
    public  void afterEach(){
        PLANET.setPlanetId(null);
    }
    @Test
    public void createPlanet_WithValidData_ReturnsPlanet() {
        //Arrange
        Planet planet = planetRepository.save(PLANET);
        //Act
        Planet sut = testEntityManager.find(Planet.class, planet.getPlanetId());
        //Assert
        assertThat(sut).isNotNull();
        assertThat(planet.getName()).isEqualTo(sut.getName());
        assertThat(planet.getClimate()).isEqualTo(sut.getClimate());
        assertThat(planet.getTerrain()).isEqualTo(sut.getTerrain());
    }

    @Test
    public void createPlanet_WithInvalidData_ThrowsException() {
        //Arrange
        //Act
        //Assert
        assertThatThrownBy(() -> planetRepository.save(EMPTY_PLANET)).isInstanceOf(RuntimeException.class);
        assertThatThrownBy(() -> planetRepository.save(INVALID_PLANET)).isInstanceOf(RuntimeException.class);

    }

    @Test
    public void createPlanet_WithExistingName_ThrowsException(){
        //Arrange
        // o entitymanager fica monitorando o objeto criado, se tentar salvar no banco assim ele sabe que o objeto já
        // foi criado e apenas atualiza ao invés de tentar salvar uma nova instancia, necessário usar o método detach
        Planet duplicatedPlanet = testEntityManager.persistFlushFind(PLANET);
        testEntityManager.detach(duplicatedPlanet);
        duplicatedPlanet.setPlanetId(null);
        //Assert
        assertThatThrownBy(()->planetRepository.save(duplicatedPlanet)).isInstanceOf(RuntimeException.class);
    }

    @Test
    public void getPlanet_ByExistingId_ReturnsPlanet(){

        Planet planet = testEntityManager.persistFlushFind(PLANET);
        Optional<Planet> optionalPlanet = planetRepository.findById(planet.getPlanetId());

        assertThat(optionalPlanet).isNotNull();
        assertThat(optionalPlanet).isNotEmpty();
        assertThat(planet.getPlanetId()).isEqualTo(optionalPlanet.get().getPlanetId());
    }

    @Test
    public void getPlanet_ByUnexistingId_ReturnsEmpty() {
         Optional<Planet> planetOptional = planetRepository.findById(1L);
         assertThat(planetOptional).isEmpty();

    }
}
