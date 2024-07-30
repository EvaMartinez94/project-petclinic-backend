package com.veterinaryClinic.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.veterinaryClinic.models.Patient;
import com.veterinaryClinic.services.PatientServices;

@RestController
@RequestMapping("/api/vc")
public class PatientControllers {

  @Autowired
    private PatientServices patientService;

    @PostMapping(path = "/patient")
    public ResponseEntity<Patient> createPatient(@RequestBody Patient newPatient) {
      Patient patient = patientService.createPatient(newPatient);
      return new ResponseEntity<>(patient, HttpStatus.CREATED);
    }
    
    @GetMapping(path = "/patient")
    public List<Patient> getAllPatients(){
        return patientService.getAllPatients();
    }

    @GetMapping(path = "patient/{id}")
    public Optional<Patient> getPatientbyId(@PathVariable Long id){
        return patientService.getPatientbyId(id);
    }

    @GetMapping(path = "/patient/tn/{tutorName}")
    public List<Patient> getByTutorName(@PathVariable String tutorName){
        return patientService.getByTutorName(tutorName);
    }

    @GetMapping(path = "/patient/in/{identificationNumber}")
    public List<Patient> getByIdentificationNumber(@PathVariable Long identificationNumber){
        return patientService.getByIdentificationNumber(identificationNumber);
    }
  
    @PutMapping("/patient/{id}")
    public void updatedPatient(@RequestBody Long patient_id , @PathVariable Patient patient) {
    patientService.updatePatient(patient_id, patient);

  }

}
