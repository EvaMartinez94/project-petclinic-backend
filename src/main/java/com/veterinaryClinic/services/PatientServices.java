package com.veterinaryClinic.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.veterinaryClinic.models.Patient;
import com.veterinaryClinic.repositories.IPatientRepository;

public class PatientServices {



//CREATE
     @Autowired IPatientRepository iPatientRepository;


    public Patient cretePatient(Patient newPatient){
        return iPatientRepository.save(newPatient);

    }



}
