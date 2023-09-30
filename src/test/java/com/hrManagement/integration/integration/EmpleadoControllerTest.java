package com.hrManagement.integration.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hrManagement.controller.dto.EmpleadoDTO;
import com.hrManagement.logica.RolEnum;
import com.hrManagement.modelo.Empleado;
import com.hrManagement.repository.EmpleadoRepository;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.SmartInstantiationAwareBeanPostProcessor;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.function.BiConsumer;

import static com.hrManagement.logica.RolEnum.DESARROLLADOR;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT )
@ActiveProfiles(profiles = "test")
@RunWith(SpringRunner.class)

@AutoConfigureMockMvc

class EmpleadoControllerTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeEach
    void setUp() {
    }

    private final ObjectMapper mapper = new ObjectMapper();
    @Test
    void agregarEmpleado() throws Exception {
        EmpleadoDTO empleadoDTO = buildEmpleadoDTO();
        mvc.perform(MockMvcRequestBuilders
                .post("/empleados/agregar")
                .content(mapper.writeValueAsString(empleadoDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void obtenerDatosEmpleadoPorID() throws Exception {
        EmpleadoDTO empleadoDTO = buildEmpleadoDTO();
        mvc.perform(get("/empleados/{codigo}",1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andDo(print());
    }

    @Test
    void obtenerEmpleadosPorCargo() throws Exception{
        EmpleadoDTO empleadoDTO = buildEmpleadoDTO();
        mvc.perform(MockMvcRequestBuilders.get("/empleados/{rol}",DESARROLLADOR)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    void obtenerTodosLosEmpleados() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/empleados/todos")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

    }

    @Test
    void modificarEmpleado() throws Exception{
        EmpleadoDTO empleadoDTO = buildEmpleadoDTO();
        empleadoDTO.setRol(RolEnum.valueOf("SCRUM_MANAGER"));
        mvc.perform(MockMvcRequestBuilders.put("/empleados/modificar/{codigo}",1)
                .content(mapper.writeValueAsString(empleadoDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isResetContent());


    }

    @Test
    void eliminarEmpleado() throws Exception {
        EmpleadoDTO empleadoDTO = buildEmpleadoDTO();
        mvc.perform(MockMvcRequestBuilders.delete("/empleados/eliminar/{codigo}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());


    }
    private EmpleadoDTO buildEmpleadoDTO() {
        EmpleadoDTO empleadoDTO = new EmpleadoDTO();
        empleadoDTO.setCodigo(1);
        empleadoDTO.setNombre("juanito");
        empleadoDTO.setEdad(45);
        empleadoDTO.setRol(RolEnum.valueOf("DESARROLLADOR"));
        empleadoDTO.setEmail("juanitogr@gmail.com");
        empleadoDTO.setNumeroTelefonico(1238527913);
        empleadoDTO.setResponsabilidades("reponsabilidades");
        return empleadoDTO;
    }
}