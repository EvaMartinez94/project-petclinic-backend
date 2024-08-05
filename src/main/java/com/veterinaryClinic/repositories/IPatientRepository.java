package com.veterinaryClinic.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.veterinaryClinic.models.Patient;

public interface IPatientRepository extends JpaRepository<Patient, Long> {

    List<Patient> findByTutorName(String tutorName);
    List<Patient> findByIdentificationNumber(Long identificationNumber);
    
}