package com.hrManagement.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hrManagement.controller.dto.PerfilEmpleadoDTO;
import com.hrManagement.logica.PerfilEmpleadoLogica;
import com.hrManagement.modelo.PerfilEmpleado;
import com.hrManagement.repository.EmpleadoRepository;
import com.hrManagement.repository.PerfilEmpleadoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(profiles = "test")
@AutoConfigureMockMvc
class PerfilEmpleadoControllerITest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PerfilEmpleadoLogica perfilEmpleadoLogica;

    @Autowired
    private PerfilEmpleadoRepository perfilEmpleadoRepository;

    @Autowired
    private EmpleadoRepository empleadoRepository;

    private PerfilEmpleadoDTO perfilEmpleadoDTO;

    @BeforeEach
    public void setUp() {
        perfilEmpleadoDTO = new PerfilEmpleadoDTO();
        perfilEmpleadoDTO.setCodigo(4);
        perfilEmpleadoDTO.setNombre("Juan Perez");
        perfilEmpleadoDTO.setHabilidades("Java, Spring");
        perfilEmpleadoDTO.setExperiencia("3 años");
        perfilEmpleadoDTO.setCertificaciones("Certificación Java");
        perfilEmpleadoDTO.setEliminar(false);
    }
/*
    @Test
    public void agregarPerfilEmpleadoTest() throws Exception {
        perfilEmpleadoDTO.setCodigo(4);

        mockMvc.perform(post("/perfilEmpleado/agregar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(perfilEmpleadoDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("No se puede crear el perfil ya que no hay un empleado con ese código"));

        Optional<PerfilEmpleado> perfilEmpleadoOptional = perfilEmpleadoRepository.findById(perfilEmpleadoDTO.getCodigo());
        assertTrue(perfilEmpleadoOptional.isPresent());
    }
    */
    @Test
    void agregarPerfilEmpleadoConEmpleadoNoExisteTest() throws Exception {
        perfilEmpleadoDTO.setCodigo(999);

        mockMvc.perform(post("/perfilEmpleado/agregar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(perfilEmpleadoDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("No se puede crear el perfil ya que no hay un empleado con ese código"));

        Optional<PerfilEmpleado> perfilEmpleadoOptional = perfilEmpleadoRepository.findById(perfilEmpleadoDTO.getCodigo());
        assertFalse(perfilEmpleadoOptional.isPresent());
    }

    @Test
    void obtenerDatosEmpleadoPorIDTest() throws Exception {
        PerfilEmpleado perfilEmpleado = new PerfilEmpleado(perfilEmpleadoDTO.getCodigo(), perfilEmpleadoDTO.getNombre(),
                perfilEmpleadoDTO.getHabilidades(), perfilEmpleadoDTO.getExperiencia(), perfilEmpleadoDTO.getCertificaciones(), false);
        perfilEmpleadoRepository.save(perfilEmpleado);

        mockMvc.perform(get("/perfilEmpleado/{id}", perfilEmpleadoDTO.getCodigo()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.codigo", is(perfilEmpleadoDTO.getCodigo())))
                .andExpect(jsonPath("$.nombre", is(perfilEmpleadoDTO.getNombre())))
                .andExpect(jsonPath("$.habilidades", is(perfilEmpleadoDTO.getHabilidades())))
                .andExpect(jsonPath("$.experiencia", is(perfilEmpleadoDTO.getExperiencia())))
                .andExpect(jsonPath("$.certificaciones", is(perfilEmpleadoDTO.getCertificaciones())))
                .andExpect(jsonPath("$.eliminar", is(false)));
    }

    @Test
    void obtenerDatosEmpleadoPorIDNoExisteTest() throws Exception {
        mockMvc.perform(get("/perfilEmpleado/{id}", 999))
                .andExpect(status().isNotFound());
    }

    @Test
    void obtenerTodosLosPerfilesDeEmpleadosTest() throws Exception {
        PerfilEmpleado perfilEmpleado1 = new PerfilEmpleado(1, "Juan Perez", "Java, Spring", "3 años", "Certificación Java", false);
        PerfilEmpleado perfilEmpleado2 = new PerfilEmpleado(2, "Maria Garcia", "Python, Django", "2 años", "Certificación Python", false);
        perfilEmpleadoRepository.saveAll(Arrays.asList(perfilEmpleado1, perfilEmpleado2));

        mockMvc.perform(get("/perfilEmpleado/todos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].codigo", is(perfilEmpleado1.getCodigo())))
                .andExpect(jsonPath("$[0].nombre", is(perfilEmpleado1.getNombre())))
                .andExpect(jsonPath("$[0].habilidades", is(perfilEmpleado1.getHabilidades())))
                .andExpect(jsonPath("$[0].experiencia", is(perfilEmpleado1.getExperiencia())))
                .andExpect(jsonPath("$[0].certificaciones", is(perfilEmpleado1.getCertificaciones())))
                .andExpect(jsonPath("$[0].eliminar", is(false)))
                .andExpect(jsonPath("$[1].codigo", is(perfilEmpleado2.getCodigo())))
                .andExpect(jsonPath("$[1].nombre", is(perfilEmpleado2.getNombre())))
                .andExpect(jsonPath("$[1].habilidades", is(perfilEmpleado2.getHabilidades())))
                .andExpect(jsonPath("$[1].experiencia", is(perfilEmpleado2.getExperiencia())))
                .andExpect(jsonPath("$[1].certificaciones", is(perfilEmpleado2.getCertificaciones())))
                .andExpect(jsonPath("$[1].eliminar", is(false)));
    }

    @Test
    void eliminarPerfilEmpleadoTest() throws Exception {
        PerfilEmpleado perfilEmpleado = new PerfilEmpleado(perfilEmpleadoDTO.getCodigo(), perfilEmpleadoDTO.getNombre(),
                perfilEmpleadoDTO.getHabilidades(), perfilEmpleadoDTO.getExperiencia(), perfilEmpleadoDTO.getCertificaciones(), false);
        perfilEmpleadoRepository.save(perfilEmpleado);

        mockMvc.perform(delete("/perfilEmpleado/eliminar/{codigo}", perfilEmpleadoDTO.getCodigo()))
                .andExpect(status().isOk())
                .andExpect(content().string("Perfil del empleado desactivado correctamente"));

        Optional<PerfilEmpleado> perfilEmpleadoOptional = perfilEmpleadoRepository.findById(perfilEmpleadoDTO.getCodigo());
        assertTrue(perfilEmpleadoOptional.isPresent());
        assertTrue(perfilEmpleadoOptional.get().isEliminar());
    }

}
