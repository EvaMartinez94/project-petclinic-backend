package com.veterinaryClinic.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.veterinaryClinic.models.Patient;
import com.veterinaryClinic.repositories.IPatientRepository;

public class PatientServices {



     @Autowired IPatientRepository iPatientRepository;

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

}