package study.automatedtestscourse.service;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import study.automatedtestscourse.models.Planet;
import study.automatedtestscourse.repository.PlanetRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
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
    public void createPlanet_WithValidData_ReturnsPlanet(){
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
}
