package com.veterinaryClinic.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.veterinaryClinic.models.Patient;
import com.veterinaryClinic.models.PatientDTO;
import com.veterinaryClinic.repositories.IPatientRepository;


@Service
public class PatientServices {

  @Autowired IPatientRepository iPatientRepository;

  public Patient createPatient(Patient newPatient){
    return iPatientRepository.save(newPatient);

  }

  public List<Patient> getAllPatients(){
    return (List<Patient>) iPatientRepository.findAll();
 }

 public Optional<Patient> getPatientbyId(Long id){
    Patient patient = iPatientRepository.findById(id).orElseThrow();
    return Optional.of(patient);
 }

 public List<Patient> getByTutorName(String tutorName){
    return (List<Patient>) iPatientRepository.findByTutorName(tutorName);
  }
  public List<Patient> getByIdentificationNumber(Long identificationNumber){
    return (List<Patient>) iPatientRepository.findByIdentificationNumber(identificationNumber);
 }

 public void deletePatient(Long patient_id){
  iPatientRepository.deleteById(patient_id);

 }
 public Patient updatePatient(Long id, PatientDTO patientDTO) {
        Optional<Patient> existingPatientOpt = iPatientRepository.findById(id);

        if (existingPatientOpt.isPresent()) {
            Patient existingPatient = existingPatientOpt.get();
            // Actualizar los campos del paciente
            existingPatient.setIdentificationNumber(patientDTO.getIdentificationNumber());
            existingPatient.setName(patientDTO.getName());
            existingPatient.setAge(patientDTO.getAge());
            existingPatient.setRace(patientDTO.getRace());
            existingPatient.setGender(patientDTO.getGender());
            existingPatient.setTutorName(patientDTO.getTutorName());
            existingPatient.setTutorPhone(patientDTO.getTutorPhone());
            existingPatient.setUrl(patientDTO.getUrl());
            return iPatientRepository.save(existingPatient);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found with id " + id);
        }
    }   

}

