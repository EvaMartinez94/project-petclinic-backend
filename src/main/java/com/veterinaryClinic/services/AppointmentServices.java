package com.veterinaryClinic.services;

import com.veterinaryClinic.repositories.IAppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentServices {

    @Autowired
    IAppointmentRepository iAppointmentRepository;

    public void deleteAppointment(Integer id){
        iAppointmentRepository.deleteById(id);
    }
}
