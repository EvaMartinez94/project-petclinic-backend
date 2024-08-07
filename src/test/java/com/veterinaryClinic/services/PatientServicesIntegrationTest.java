package com.veterinaryClinic.services;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.web.server.ResponseStatusException;

import com.veterinaryClinic.models.Patient;
import com.veterinaryClinic.models.PatientDTO;
import com.veterinaryClinic.repositories.IPatientRepository;

class PatientServicesIntegrationTest {

    @Mock
    private IPatientRepository iPatientRepository;

    @InjectMocks
    private PatientServices patientServices;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createPatient_ShouldReturnCreatedPatient() {
        Patient newPatient = new Patient();
        when(iPatientRepository.save(newPatient)).thenReturn(newPatient);

        Patient createdPatient = patientServices.createPatient(newPatient);

        assertNotNull(createdPatient);
        assertEquals(newPatient, createdPatient);
    }

    @Test
    void getAllPatients_ShouldReturnListOfPatients() {
        Patient patient = new Patient();
        Patient patient2 = new Patient();
        when(iPatientRepository.findAll()).thenReturn(Arrays.asList(patient, patient2));

        var patients = patientServices.getAllPatients();

        assertNotNull(patients);
        assertEquals(2, patients.size());
    }

    @Test
    void getPatientById_ShouldReturnPatient_WhenExists() {
        Patient patient = new Patient();
        when(iPatientRepository.findById(1L)).thenReturn(Optional.of(patient));

        Optional<Patient> foundPatient = patientServices.getPatientbyId(1L);

        assertTrue(foundPatient.isPresent());
        assertEquals(patient, foundPatient.get());
    }

    @Test
    void getPatientById_ShouldThrowException_WhenNotFound() {
        when(iPatientRepository.findById(1L)).thenReturn(Optional.empty());

        // Ajusta aquí para esperar NoSuchElementException
        assertThrows(NoSuchElementException.class, () -> patientServices.getPatientbyId(1L).orElseThrow());
}

    @Test
    void updatePatient_ShouldReturnUpdatedPatient_WhenExists() {
        Patient existingPatient = new Patient();
        existingPatient.getPatient_id();
        PatientDTO updatedData = new PatientDTO();
        updatedData.setName("UpdatedName");

        when(iPatientRepository.findById(1L)).thenReturn(Optional.of(existingPatient));
        when(iPatientRepository.save(any(Patient.class))).thenReturn(existingPatient);

        Patient updatedPatient = patientServices.updatePatient(1L, updatedData);

        assertEquals("UpdatedName", updatedPatient.getName());
    }

    @Test
    void updatePatient_ShouldThrowException_WhenNotFound() {
        when(iPatientRepository.findById(1L)).thenReturn(Optional.empty());

        PatientDTO updatedData = new PatientDTO();
        assertThrows(ResponseStatusException.class, () -> patientServices.updatePatient(1L, updatedData));
    }
    @Test
    void getByIdentificationNumber_ShouldReturnPatients_WhenExists() {
        Patient patient1 = new Patient();
        Patient patient2 = new Patient();
        when(iPatientRepository.findByIdentificationNumber(12345L)).thenReturn(Arrays.asList(patient1, patient2));

        var patients = patientServices.getByIdentificationNumber(12345L);

        assertNotNull(patients);
        assertEquals(2, patients.size());
        assertTrue(patients.contains(patient1));
        assertTrue(patients.contains(patient2));
    }

}
