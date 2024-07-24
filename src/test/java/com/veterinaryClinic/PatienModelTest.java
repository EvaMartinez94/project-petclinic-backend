package com.veterinaryClinic;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.veterinaryClinic.models.Patient;
import com.veterinaryClinic.repositories.IPatientRepository;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional
public class PatienModelTest {

    @Autowired
    private IPatientRepository patientRepository;

    private Patient patient;

    @BeforeEach
    public void setUp() {
        // Limpiar la base de datos antes de cada prueba
        patientRepository.deleteAll();

        patient = new Patient();
        patient.setIdentificationNumber(System.currentTimeMillis()); 
        patient.setName("Bobby");
        patient.setAge(5);
        patient.setRace("Labrador");
        patient.setGender("Male");
        patient.setTutorName("John");
        patient.setTutorPhone("622-622-123");
        patient.setTreatment("Regular checkup");
    }

    @Test
    public void testCreatePatient() {
        Patient savedPatient = patientRepository.save(patient);
        assertThat(savedPatient).isNotNull();
        assertThat(savedPatient.getPatient_id()).isNotNull();
        assertThat(savedPatient.getIdentificationNumber()).isEqualTo(patient.getIdentificationNumber());
    }

    @Test
    public void testReadPatient() {
        Patient savedPatient = patientRepository.save(patient);
        Patient foundPatient = patientRepository.findById(savedPatient.getPatient_id()).orElse(null);
        assertThat(foundPatient).isNotNull();
        assertThat(foundPatient.getName()).isEqualTo("Bobby");
    }

    @Test
    public void testUpdatePatient() {
        Patient savedPatient = patientRepository.save(patient);
        Patient foundPatient = patientRepository.findById(savedPatient.getPatient_id()).orElse(null);
        assertThat(foundPatient).isNotNull();
        
        foundPatient.setAge(6);
        patientRepository.save(foundPatient);
        
        Patient updatedPatient = patientRepository.findById(foundPatient.getPatient_id()).orElse(null);
        assertThat(updatedPatient).isNotNull();
        assertThat(updatedPatient.getAge()).isEqualTo(6);
    }

    @Test
    public void testDeletePatient() {
        Patient savedPatient = patientRepository.save(patient);
        patientRepository.deleteById(savedPatient.getPatient_id());
        Patient foundPatient = patientRepository.findById(savedPatient.getPatient_id()).orElse(null);
        assertThat(foundPatient).isNull();
    }

    @Test
    public void testFindAllPatients() {
        Patient patient2 = new Patient();
        patient2.setIdentificationNumber(System.currentTimeMillis() + 1);
        patient2.setName("Max");
        patient2.setAge(3);
        patient2.setRace("Siames");
        patient2.setGender("Male");
        patient2.setTutorName("Jane");
        patient2.setTutorPhone("622-622-567");
        patient2.setTreatment("Vaccination");

        patientRepository.save(patient);
        patientRepository.save(patient2);

        List<Patient> patients = patientRepository.findAll();
        assertThat(patients).hasSize(2);
        assertThat(patients).extracting(Patient::getName).containsExactlyInAnyOrder("Bobby", "Max");
    }
}
