package com.veterinaryClinic.services;

import com.veterinaryClinic.models.Appointment;
import com.veterinaryClinic.repositories.IAppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Optional;

public class AppointmentServices {
    @Autowired
    IAppointmentRepository iAppointmentRepository;

    public ArrayList<Appointment> getAllAppointment(){
        return (ArrayList<Appointment>) iAppointmentRepository.findAll();
    }

    public Optional<Appointment> getAppointmentId(int id){
        Appointment appointment = iAppointmentRepository.findById(id).orElseThrow();
        return Optional.of(appointment);
    }


}
