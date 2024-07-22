package com.veterinaryClinic.services;

import com.veterinaryClinic.models.Appointment;
import com.veterinaryClinic.repositories.IAppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Optional;

public class AppointmentServices {
    @Autowired
    IAppointmentRepository iAppointmentRepository;

    public Appointment createAppointment (Appointment newAppointment){
        return iAppointmentRepository.save(newAppointment);
    }
}
