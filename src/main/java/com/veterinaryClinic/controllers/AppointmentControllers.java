package com.veterinaryClinic.controllers;

import com.veterinaryClinic.models.Appointment;
import com.veterinaryClinic.services.AppointmentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/api/vc")
@CrossOrigin(origins = "*")
public class AppointmentControllers {

    @Autowired
    AppointmentServices appointmentServices;

    @PutMapping(path = "/appointment/{id}")
    public void updateAppointment(@RequestBody Appointment appointment, @PathVariable Integer id){
        appointmentServices.updateAppointment(appointment, id);
    }
    AppointmentServices appointmentServices;

    @PostMapping(path = "/appointment")
    public Appointment createAppointment (@RequestBody Appointment newAppointment){
        return appointmentServices.createAppointment(newAppointment);
    @GetMapping(path = "/appointment")
    public ArrayList<Appointment> getAllAppointment() {
        return appointmentServices.getAllAppointment();
    }

    @GetMapping(path = "/appointment/{id}")
    public Optional<Appointment> getAppointmentId(@PathVariable int id){
        return appointmentServices.getAppointmentId(id);
    }
}

    @DeleteMapping(path = "/appointment/{id}")
    public void deleteAppointment(@PathVariable Integer id){
        appointmentServices.deleteAppointment(id);
    }
}