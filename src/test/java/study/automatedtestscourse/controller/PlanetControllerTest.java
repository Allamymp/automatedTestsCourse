package study.automatedtestscourse.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import study.automatedtestscourse.service.PlanetService;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static study.automatedtestscourse.common.PlanetConstants.*;

//habilita testes via http request
// especificado apenas o PlanetController no teste
@WebMvcTest(PlanetController.class)
public class PlanetControllerTest {
    @Autowired
    private MockMvc mockMvc;
    //objectMapper será utilizado para serializar objetos como string para serem enviados via http
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private PlanetService planetService;

    @Test
    public void createPlanet_WithValidData_ReturnsCreated() throws Exception {
        //Arrange
        when(planetService.create(PLANET)).thenReturn(PLANET);

        //passar PLANET diretamente não é possivel pq não há a serialização do objeto para ser enviado na requisição
        mockMvc
                .perform(post("/planets").content(objectMapper.writeValueAsString(PLANET))
                        .contentType(MediaType.APPLICATION_JSON))
                //verifica o status do retorno
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$").value(PLANET));
    }

    @Test
    public void createPlanet_WithInvalidData_ReturnsBadRequest() throws Exception {
        mockMvc
                .perform(
                        post("/planets").content(objectMapper.writeValueAsString(INVALID_PLANET))
                                .contentType(MediaType.APPLICATION_JSON))
                //verifica o status do retorno
                .andExpect(status().isUnprocessableEntity());

        mockMvc
                .perform(
                        post("/planets").content(objectMapper.writeValueAsString(EMPTY_PLANET))
                                .contentType(MediaType.APPLICATION_JSON))
                //verifica o status do retorno
                .andExpect(status().isUnprocessableEntity());

    }

    @Test
    public void createPlanet_WithExistingName_ReturnsConflict() throws Exception {
        when(planetService.create(any())).thenThrow(DataIntegrityViolationException.class);

        mockMvc
                .perform(
                        post("/planets").content(objectMapper.writeValueAsString(PLANET))
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict());
    }

    @Test
    public void getPlanet_ByExistingId_ReturnsPlanet() throws Exception{
        when(planetService.findById(1L)).thenReturn(Optional.of(PLANET));

        mockMvc
                .perform(
                        get("/planets/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(PLANET));
    }
    @Test
    public void getPlanet_ByUnexistingId_ReturnsNotFound() throws Exception{
        mockMvc
                .perform(
                        get("/planets/1"))
                .andExpect(status().isNotFound());

    }
    @Test
    public  void getPlanet_ByExistingName_ReturnsPlanet()throws Exception{
        when(planetService.findByName("name")).thenReturn(Optional.of(PLANET));

        mockMvc
                .perform(
                        //dispara a requisição get
                        get("/planets/name/{name}",PLANET.getName()))
                //verifica se o status da resposta foi 200ok
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(PLANET));
    }
    @Test
    public void getPlanet_ByUnexistingName_ReturnsNotFound() throws Exception{
        mockMvc
                .perform(
                        get("/name/planetName"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void listPlanets_ReturnsFilteredPlanets() throws Exception{

    }  @Test
    public void listPlanets_ReturnsNoPlanets() throws Exception{

    }
}
