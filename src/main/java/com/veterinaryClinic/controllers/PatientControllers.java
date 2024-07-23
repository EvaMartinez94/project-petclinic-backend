package com.veterinaryClinic.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.veterinaryClinic.models.Patient;
import com.veterinaryClinic.services.PatientServices;


@RestController
@RequestMapping("/vc/patient")
public class PatientControllers {
    

    @Autowired
    private PatientServices patientService;

// CREATE
    @PostMapping
    public ResponseEntity<Patient> createPatient(@RequestBody Patient newPatient) {
        Patient patient = patientService.cretePatient(newPatient);
        return new ResponseEntity<>(patient, HttpStatus.CREATED);
    }


}
