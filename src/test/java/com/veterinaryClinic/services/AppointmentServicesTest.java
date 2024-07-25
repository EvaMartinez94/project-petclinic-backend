package com.veterinaryClinic.services;

import com.veterinaryClinic.models.Appointment;
import com.veterinaryClinic.repositories.IAppointmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class AppointmentServicesTest {

  @Mock private IAppointmentRepository iAppointmentRepository;
  @InjectMocks private AppointmentServices appointmentServices;

  private Appointment appointmentDuque;
  private Appointment appointmentKoda;
  private ArrayList<Appointment> appointmentList = new ArrayList<>();

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);

    appointmentDuque = new Appointment();
    appointmentDuque.setDate(LocalDate.of(2024, 10, 10));
    appointmentDuque.setTime(LocalTime.of(19, 30));
    appointmentDuque.setPatient("Duque");
    appointmentDuque.setEmergency(false);
    appointmentDuque.setReason("lele pancha");
    appointmentDuque.setPast(false);

    appointmentKoda = new Appointment();
    appointmentKoda.setDate(LocalDate.of(2024, 04, 29));
    appointmentKoda.setTime(LocalTime.of(12, 10));
    appointmentKoda.setPatient("Koda");
    appointmentKoda.setEmergency(true);
    appointmentKoda.setReason("caca explosiva");
    appointmentKoda.setPast(true);
  }
  @Test
  public void createAppointment(){
    when(iAppointmentRepository.(any(Appointment.class))).thenReturn(appointmentDuque);
    Appointment newAppointment = appointmentServices.createAppointment(appointmentDuque);

    assertNotNull(newAppointment);
    assertEquals(2, newAppointment.getId());
    assertEquals(LocalDate.of(2024, 10, 10), newAppointment.getDate());
    assertEquals(LocalTime.of(19, 30), newAppointment.getTime());
    assertEquals("patientDuque", newAppointment.getPatient());
    assertEquals(false, newAppointment.isEmergency());
    assertEquals("lele pancha", newAppointment.getReason());
    assertEquals(false, newAppointment.isPast());
  }
}
