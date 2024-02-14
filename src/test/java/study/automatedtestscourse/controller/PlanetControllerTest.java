package study.automatedtestscourse.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import study.automatedtestscourse.service.PlanetService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static study.automatedtestscourse.common.PlanetConstants.PLANET;

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
        mockMvc.perform(post("/planets").content(objectMapper.writeValueAsString(PLANET))
                        .contentType(MediaType.APPLICATION_JSON))
                //verifica o status do retorno
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$").value(PLANET));
    }
}
