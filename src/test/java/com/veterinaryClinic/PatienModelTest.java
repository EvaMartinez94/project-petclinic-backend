package com.veterinaryClinic;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.veterinaryClinic.models.Patient;
import com.veterinaryClinic.repositories.IPatientRepository;

public class PatienModelTest {

@Autowired
    private IPatientRepository patientRepository;

    private Patient patient;

    @BeforeEach
    public void setUp() {
        
        patient = new Patient();
        patient.setIdentificationNumber(1234L);
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
        assertThat(savedPatient.getId()).isNotNull();
        assertThat(savedPatient.getIdentificationNumber()).isEqualTo(1234L);
    }

    @Test
    public void testReadPatient() {
        patientRepository.save(patient);
        Patient foundPatient = patientRepository.findById(patient.getId()).orElse(null);
        assertThat(foundPatient).isNotNull();
        assertThat(foundPatient.getName()).isEqualTo("Bobby");
    }

    @Test
    public void testUpdatePatient() {
        patientRepository.save(patient);
        Patient foundPatient = patientRepository.findById(patient.getId()).orElse(null);
        assertThat(foundPatient).isNotNull();
        
        foundPatient.setAge(6);
        patientRepository.save(foundPatient);
        
        Patient updatedPatient = patientRepository.findById(foundPatient.getId()).orElse(null);
        assertThat(updatedPatient).isNotNull();
        assertThat(updatedPatient.getAge()).isEqualTo(6);
    }

    @Test
    public void testDeletePatient() {
        patientRepository.save(patient);
        patientRepository.deleteById(patient.getId());
        Patient foundPatient = patientRepository.findById(patient.getId()).orElse(null);
        assertThat(foundPatient).isNull();
    }

    @Test
    public void testFindAllPatients() {
        Patient patient2 = new Patient();
        patient2.setIdentificationNumber(5678L);
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
