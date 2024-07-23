package com.veterinaryClinic.repositories;

import com.veterinaryClinic.models.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAppointmentRepository extends JpaRepository <Appointment, Integer> {
}
