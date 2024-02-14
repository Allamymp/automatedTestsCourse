package study.automatedtestscourse.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import study.automatedtestscourse.models.Planet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static study.automatedtestscourse.common.PlanetConstants.*;


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
}
