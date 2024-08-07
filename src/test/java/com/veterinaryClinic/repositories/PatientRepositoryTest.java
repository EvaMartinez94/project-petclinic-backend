package com.veterinaryClinic.repositories;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.veterinaryClinic.models.Patient;

@SpringBootTest
@Transactional
class PatientRepositoryTest {

    @Autowired
    private IPatientRepository iPatientRepository;

    @Test
    void whenSaved_thenFindsByIdentificationNumber() {
        Patient patient = new Patient();
        patient.setIdentificationNumber(777L);
        patient.setName("Fido");
        patient.setTutorName("John Doe");
        patient.setTutorPhone("555-1234");
        iPatientRepository.save(patient);

        Optional<Patient> foundPatient = iPatientRepository.findByIdentificationNumber(777L).stream().findFirst();

        assertTrue(foundPatient.isPresent());
        assertEquals(777L, foundPatient.get().getIdentificationNumber());
        assertEquals("Fido", foundPatient.get().getName());
        assertEquals("John Doe", foundPatient.get().getTutorName());
        assertEquals("555-1234", foundPatient.get().getTutorPhone());
    }

    @Test
    void whenFindByNonExistingIdentificationNumber_thenReturnsEmpty() {
        List<Patient> foundPatient = iPatientRepository.findByIdentificationNumber(111L);
        assertTrue(foundPatient.isEmpty());
    }
}
