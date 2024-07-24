package com.veterinaryClinic.controllers;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.veterinaryClinic.models.Appointment;
import com.veterinaryClinic.services.AppointmentServices;

@RestController
@RequestMapping("/api/vc")
@CrossOrigin(origins = "*")
public class AppointmentControllers {

  @Autowired AppointmentServices appointmentServices;

  @PutMapping(path = "/appointment/{id}")
  public void updateAppointment(@RequestBody Appointment appointment, @PathVariable Integer id) {
    appointmentServices.updateAppointment(appointment, id);
  }

  @PostMapping(path = "/appointment")
  public Appointment createAppointment(@RequestBody Appointment newAppointment) {
    return appointmentServices.createAppointment(newAppointment);
  }

  @GetMapping(path = "/appointment")
  public ArrayList<Appointment> getAllAppointment() {
    return appointmentServices.getAllAppointment();
  }

  @GetMapping(path = "/appointment/{id}")
  public Optional<Appointment> getAppointmentId(@PathVariable int id) {
    return appointmentServices.getAppointmentId(id);
  }

  @DeleteMapping(path = "/appointment/{id}")
  public void deleteAppointment(@PathVariable Integer id) {
    appointmentServices.deleteAppointment(id);
  }
}
