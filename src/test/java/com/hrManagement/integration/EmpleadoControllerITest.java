package com.hrManagement.integration;

import com.hrManagement.controller.dto.EmpleadoDTO;
import com.hrManagement.logica.EmpleadoLogica;
import com.hrManagement.logica.RolEnum;
import com.hrManagement.modelo.Empleado;
import com.hrManagement.repository.EmpleadoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT )
@ActiveProfiles(profiles = "test")
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
class EmpleadoControllerITest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmpleadoLogica empleadoLogica;

    @Autowired
    private EmpleadoRepository empleadoRepository;

    private EmpleadoDTO empleadoDTO;

    @BeforeEach
    public void setUp() {
        empleadoDTO = new EmpleadoDTO();
        empleadoDTO.setCodigo(4);
        empleadoDTO.setNombre("Juan Perez");
        empleadoDTO.setEdad(30);
        empleadoDTO.setRol(RolEnum.DESARROLLADOR);
        empleadoDTO.setEmail("juan.perez@example.com");
        empleadoDTO.setNumeroTelefonico(1234567890);
        empleadoDTO.setResponsabilidades("Desarrollar software");
        empleadoDTO.setEliminar(false);
    }

    @Test
    void agregarEmpleadoTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/empleados/agregar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"codigo\": 5,\n" +
                                "    \"nombre\": \"Juan Sebastian\",\n" +
                                "    \"edad\": 20,\n" +
                                "    \"rol\": \"DESARROLLADOR\",\n" +
                                "    \"email\": \"juan.sebastian@example.com\",\n" +
                                "    \"numeroTelefonico\": 123456789,\n" +
                                "    \"responsabilidades\": \"Desarrollar software\",\n" +
                                "    \"eliminar\": false\n" +
                                "}"))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.content().string("Empleado agregado correctamente"));

        Empleado empleado = empleadoRepository.findById(5).orElse(null);
        assert empleado != null;
        assert empleado.getNombre().equals("Juan Sebastian");
    }

    @Test
    void obtenerEmpleadoTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/empleados/agregar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"codigo\": 4,\n" +
                                "    \"nombre\": \"Juan Perez\",\n" +
                                "    \"edad\": 30,\n" +
                                "    \"rol\": \"DESARROLLADOR\",\n" +
                                "    \"email\": \"juan.perez@example.com\",\n" +
                                "    \"numeroTelefonico\": 1234567890,\n" +
                                "    \"responsabilidades\": \"Desarrollar software\",\n" +
                                "    \"eliminar\": false\n" +
                                "}"))
                .andExpect(status().isCreated());
        mockMvc.perform(get("/empleados/4"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.codigo").value(4))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nombre").value("Juan Perez"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.edad").value(30))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rol").value("DESARROLLADOR"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("juan.perez@example.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.numeroTelefonico").value(1234567890))
                .andExpect(MockMvcResultMatchers.jsonPath("$.responsabilidades").value("Desarrollar software"));
    }

    @Test
    void actualizarEmpleadoTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/empleados/modificar/4")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"codigo\": 4,\n" +
                                "    \"nombre\": \"Juan Villalobos\",\n" +
                                "    \"edad\": 30,\n" +
                                "    \"rol\": \"ADMINISTRADOR\",\n" +
                                "    \"email\": \"juan.villalobos@example.com\",\n" +
                                "    \"numeroTelefonico\": 1234567890,\n" +
                                "    \"responsabilidades\": \"Desarrollar software\",\n" +
                                "    \"eliminar\": false\n" +
                                "}"))
                .andExpect(MockMvcResultMatchers.content().string("Empleado actualizado correctamente"));

        Empleado empleado = empleadoRepository.findById(4).orElse(null);
        assert empleado != null;
        assert empleado.getNombre().equals("Juan Villalobos");
    }

    @Test
    void eliminarEmpleadoTest() throws Exception {
        mockMvc.perform(delete("/empleados/eliminar/4"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(equalTo("Empleado desactivado correctamente")));

        Empleado empleado = empleadoRepository.findById(4).orElse(null);
        assert empleado != null;
        assert empleadoLogica.eliminarEmpleado(4);
    }
}
