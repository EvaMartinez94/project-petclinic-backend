package com.veterinaryClinic.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.veterinaryClinic.models.Patient;
import com.veterinaryClinic.repositories.IPatientRepository;

@Service
public class PatientServices {

  @Autowired IPatientRepository iPatientRepository;

  public Patient cretePatient(Patient newPatient) {
    return iPatientRepository.save(newPatient);
  }

  public List<Patient> getAllPatients() {
    return (List<Patient>) iPatientRepository.findAll();
  }

  public Optional<Patient> getPatientbyId(Long id) {
    Patient patient = iPatientRepository.findById(id).orElseThrow();
    return Optional.of(patient);
  }

  public List<Patient> getByTutorName(String tutorName) {
    return (List<Patient>) iPatientRepository.findByTutorName(tutorName);
  }

  public List<Patient> getByIdentificationNumber(Long identificationNumber) {
    return (List<Patient>) iPatientRepository.findByIdentificationNumber(identificationNumber);
  }

  public void deletePatient(Long identificationNumber) {
    iPatientRepository.deleteById(identificationNumber);
  }

  public void updatePatient(Long id, Patient patient) {
    patient.setPatient_id(id);
    iPatientRepository.save(patient);
  }
}
