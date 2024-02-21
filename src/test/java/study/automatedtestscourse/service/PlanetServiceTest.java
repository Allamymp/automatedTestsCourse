package study.automatedtestscourse.service;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import study.automatedtestscourse.models.Planet;
import study.automatedtestscourse.repository.PlanetRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;
import static study.automatedtestscourse.common.PlanetConstants.*;

// cria o contexto de teste e especifica o bean a ser adicionado
// em testes de unidade nao e necessario(geralmente) usar o springboot, usa-se so o mockito
//@SpringBootTest(classes = PlanetService.class)
@ExtendWith(MockitoExtension.class)
public class PlanetServiceTest {
    @InjectMocks
    private PlanetService planetService;
    //@MockBean
    @Mock
    private PlanetRepository planetRepository;

    // padrao de nomenclatura : operacao_estado_retornoEsperado
    @Test
    public void createPlanet_WithValidData_ReturnsPlanet() {
        // criacao de um stub para o mock do repositorio configurando um metodo
        // quando for chamado planetRepository.save(PLANET) ele retorna o PLANET
        //Arrange
        when(planetRepository.save(PLANET)).thenReturn(PLANET);


        //System Under Test (SUT)
        //Act
        Planet sut = planetService.create(PLANET);

        //Assert
        assertThat(sut).isEqualTo(PLANET);
    }

    @Test
    public void createPlanet_WhithNoValidData_ThrowsException() {
        //Arrange
        when(planetRepository.save(INVALID_PLANET)).thenThrow(RuntimeException.class);

        assertThatThrownBy(
                //Act
                () -> planetService.create(INVALID_PLANET))
                //Assert
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    public void getById_withExistingId_ReturnsPlanet() {
        //comportamento mockado
        when(planetService.findById(1L)).thenReturn(Optional.of(PLANET));

        //método a ser testado
        Optional<Planet> sut = planetService.findById(1L);

        // resultados esperados
        assertThat(sut.isPresent()).isTrue();
        assertThat(sut.get()).isEqualTo(PLANET);
    }

    @Test
    public void getById_withNonexistentId_ReturnsNull() {
        //preparatorio
        when(planetService.findById(2L)).thenReturn(Optional.empty());

        //metodo a ser testado
        Optional<Planet> sut = planetService.findById(2L);

        //resultado esperados
        assertThat(sut.isPresent()).isFalse();

    }

    @Test
    public void getByName_withExistingName_ReturnsPlanet() {
        //Arrange
        when(planetService.findByName("name")).thenReturn(Optional.of(PLANET));

        //Act
        Optional<Planet> sut = planetService.findByName("name");

        //Assert
        assertThat(sut.isPresent()).isTrue();
        assertThat(sut.get()).isEqualTo(PLANET);
    }

    @Test
    public void getByName_withNonexistentName_ReturnsNull() {
        //Arrange
        when(planetService.findByName("nonexistent name")).thenReturn(Optional.empty());

        //Act
        Optional<Planet> sut = planetService.findByName("nonexistent name");

        //Assert
        assertThat(sut.isEmpty()).isTrue();
    }

    @Test
    public void getByFilters_withExistingFilters_ReturnsPlanet() {
        //Arrange
        when(planetService.findByFilters("Wet", "Swamp")).thenReturn(PLANETS);
        //Act
        List<Planet> sut = planetService.findByFilters("Wet", "Swamp");
        //Assert
        assertThat(sut.isEmpty()).isFalse();
        assertThat(sut.getFirst()).isEqualTo(DAGOBAH);
    }

    @Test
    public void getByFilters_withNonexistentFilters_ReturnsNull() {
        //Arrange
        when(planetService.findByFilters("nonexistent climate", "nonexistent terrain"))
                .thenReturn(NULL_PLANETS);
        //Act
        List<Planet> sut = planetService.findByFilters("nonexistent climate", "nonexistent terrain");
        //Assert
        assertThat(sut.isEmpty()).isTrue();
    }

    @Test
    public void removePlanet_WithExistingId_DoesNotThrowAnyException() {
        assertThatCode(() -> planetService.deleteById(1L)).doesNotThrowAnyException();
    }

    @Test
    public void removePlanet_WithUnexistingId_ThrowsException() {
        doThrow(new RuntimeException()).when(planetService).deleteById(99L);
        assertThatThrownBy(()->planetService.deleteById(99L)).isInstanceOf(RuntimeException.class);
    }
}
