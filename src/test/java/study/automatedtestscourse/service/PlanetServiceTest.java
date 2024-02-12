package study.automatedtestscourse.service;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import study.automatedtestscourse.models.Planet;
import study.automatedtestscourse.repository.PlanetRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;
import static study.automatedtestscourse.common.PlanetConstants.INVALID_PLANET;
import static study.automatedtestscourse.common.PlanetConstants.PLANET;

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
    public void getById_withValidInput_ReturnsPlanet() {
        //comportamento mockado
        when(planetService.findById(1L)).thenReturn(Optional.of(PLANET));

        //método a ser testado
        Optional<Planet> sut = planetService.findById(1L);

        // resultados esperados
        assertThat(sut.isPresent()).isTrue();
        assertThat(sut.get()).isEqualTo(PLANET);
    }
    @Test
    public  void  getById_withInvalidInput_ReturnsNull(){
        //preparatorio
        when(planetService.findById(2L)).thenReturn(Optional.empty());

        //metodo a ser testado
        Optional<Planet> sut = planetService.findById(2L);

        //resultado esperados
        assertThat(sut.isPresent()).isFalse();

    }
}
