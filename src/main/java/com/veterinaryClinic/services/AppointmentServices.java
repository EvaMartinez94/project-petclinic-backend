package com.veterinaryClinic.services;

import com.veterinaryClinic.models.Appointment;
import com.veterinaryClinic.repositories.IAppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class AppointmentServices {

    @Autowired
    IAppointmentRepository iAppointmentRepository;

    public void updateAppointment(Appointment appointment, Integer id){
        appointment.setId(id);
        iAppointmentRepository.save(appointment);
    }

    public Appointment createAppointment (Appointment newAppointment){
        return iAppointmentRepository.save(newAppointment);
    }
}

    public void deleteAppointment(Integer id){
        iAppointmentRepository.deleteById(id);
    }
}