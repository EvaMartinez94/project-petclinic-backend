package com.veterinaryClinic.controllers;

import com.veterinaryClinic.services.AppointmentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vc")
@CrossOrigin(origins = "*")
public class AppointmentControllers {

    @Autowired
    AppointmentServices appointmentServices;

    @DeleteMapping(path = "appointment/{id}")
    public void deleteAppointment(@PathVariable Integer id){
        appointmentServices.deleteAppointment(id);
    }
}
