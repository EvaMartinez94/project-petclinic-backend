package com.veterinaryClinic.controllers;

import com.veterinaryClinic.models.Appointment;

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
    }


}

    @DeleteMapping(path = "/appointment/{id}")
    public void deleteAppointment(@PathVariable Integer id){
        appointmentServices.deleteAppointment(id);
    }
}