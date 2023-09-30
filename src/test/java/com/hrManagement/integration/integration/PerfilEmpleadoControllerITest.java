package com.hrManagement.integration.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hrManagement.controller.dto.PerfilEmpleadoDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT )
@ActiveProfiles(profiles = "test")
@RunWith(SpringRunner.class)

@AutoConfigureMockMvc

class PerfilEmpleadoControllerITest {

    /*
    @Autowired
    private MockMvc mvc;
    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeEach
    void setUp() {
    }

    private final ObjectMapper mapper = new ObjectMapper();
    @Test
    void agregarPerfiilEmpleado() throws Exception {
        PerfilEmpleadoDTO perfilEmpleadoDTO = buildPerfilEmpleadoDTO();
        mvc.perform(MockMvcRequestBuilders
                .post("/perfilempleado/agregar")
                .content(mapper.writeValueAsString(perfilEmpleadoDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void obtenerDatosEmpleadoPorID() throws Exception {
        PerfilEmpleadoDTO perfilEmpleadoDTO = buildPerfilEmpleadoDTO();
        mvc.perform(get("/perfilempleado/{codigo}",1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andDo(print());
    }

    @Test
    void obtenerTodosLosPerfilesDeEmpleados() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/perfilempleado/todos")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

    }

    @Test
    void eliminarEmpleado() throws Exception {
        PerfilEmpleadoDTO perfilEmpleadoDTO = buildPerfilEmpleadoDTO();
        mvc.perform(MockMvcRequestBuilders.delete("/perfilempleado/eliminar/{codigo}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
    private PerfilEmpleadoDTO buildPerfilEmpleadoDTO() {
        PerfilEmpleadoDTO perfilEmpleadoDTO = new PerfilEmpleadoDTO();
        perfilEmpleadoDTO.setCodigo(1);
        perfilEmpleadoDTO.setNombre("Juanito");
        perfilEmpleadoDTO.setHabilidades("Habilidades");
        perfilEmpleadoDTO.setExperiencia("Experiencias");
        perfilEmpleadoDTO.setCertificaciones("certificaciones");
        return perfilEmpleadoDTO;
    }
    */

}