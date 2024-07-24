package com.veterinaryClinic.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.veterinaryClinic.models.Patient;
import com.veterinaryClinic.repositories.IPatientRepository;



@Service
public class PatientServices {

     @Autowired IPatientRepository iPatientRepository;

    public Patient cretePatient(Patient newPatient){
        return iPatientRepository.save(newPatient);

    }



}
