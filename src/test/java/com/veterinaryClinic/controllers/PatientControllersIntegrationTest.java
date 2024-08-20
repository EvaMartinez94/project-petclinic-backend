package com.veterinaryClinic.controllers;


import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.veterinaryClinic.models.Patient;
import com.veterinaryClinic.models.PatientDTO;
import com.veterinaryClinic.services.PatientServices;

import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
class PatientControllersIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private PatientServices patientServices;

    @InjectMocks
    private PatientControllers patientControllers;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(patientControllers).build();
    }

    @Test
    public void getPatientById_ShouldReturn404_WhenPatientDoesNotExist() throws Exception {
        mockMvc.perform(get("/patients/{id}", 999L)) // Asegúrate de que este ID no exista
            .andExpect(status().isNotFound());
    }
            
    @Test
    void getAllPatients_ShouldReturn200WithPatientList() throws Exception {
        Patient patient = new Patient();
        patient.setName("Fido");
        when(patientServices.getAllPatients()).thenReturn(Arrays.asList(patient));

        mockMvc.perform(get("/api/vc/patient"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Fido"));
    }

    @Test
    void getPatientById_ShouldReturn200WithPatient_WhenPatientExists() throws Exception {
        Patient patient = new Patient();
        patient.setName("Fido");
        when(patientServices.getPatientbyId(1L)).thenReturn(Optional.of(patient));

        mockMvc.perform(get("/api/vc/patient/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Fido"));
    }

   

    @Test
    void deletePatient_ShouldReturn200_WhenPatientIsDeleted() throws Exception {
        mockMvc.perform(delete("/api/vc/patient/1"))
                .andExpect(status().isOk());
    }

    @Test
    void updatePatient_ShouldReturn200_WhenPatientIsUpdated() throws Exception {
        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setName("UpdatedName");

        mockMvc.perform(put("/api/vc/patient/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"UpdatedName\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("UpdatedName"));
    }
}
