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
    AppointmentServices AppointmentServices;
}
