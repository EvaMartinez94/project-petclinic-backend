package com.veterinaryClinic.controllers;

import com.veterinaryClinic.models.Appointment;
import com.veterinaryClinic.services.AppointmentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/vc")
@CrossOrigin(origins = "*")
public class AppointmentControllers {

    @Autowired
    AppointmentServices appointmentServices;

    @PutMapping(path = "appointment/{id}")
    public void updateAppointment(@RequestBody Appointment appointment, @PathVariable Integer id){
        appointmentServices.updateAppointment(appointment, id);
    }
}