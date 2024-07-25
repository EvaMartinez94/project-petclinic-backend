package com.veterinaryClinic.services;

import com.veterinaryClinic.models.Appointment;
import com.veterinaryClinic.models.Patient;
import com.veterinaryClinic.repositories.IAppointmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class AppointmentServicesTest {

  @Mock private IAppointmentRepository iAppointmentRepository;
  @InjectMocks private AppointmentServices appointmentServices;

  private Appointment appointmentDuque;
  private Appointment appointmentKoda;
  private ArrayList<Appointment> appointmentList = new ArrayList<>();

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    Patient patientDuque = new Patient();
    patientDuque.setName("Duque");
    Patient patientKoda = new Patient();
    patientKoda.setName("Koda");

    appointmentDuque = new Appointment();
    appointmentDuque.setId(1);
    appointmentDuque.setDate(LocalDate.of(2024, 10, 10));
    appointmentDuque.setTime(LocalTime.of(19, 30));
    appointmentDuque.setPatient(patientDuque);
    appointmentDuque.setEmergency(false);
    appointmentDuque.setReason("lele pancha");
    appointmentDuque.setPast(false);
    appointmentDuque.setTreatment("Ibuprofeno");

    appointmentKoda = new Appointment();
    appointmentKoda.setId(2);
    appointmentKoda.setDate(LocalDate.of(2024, 04, 29));
    appointmentKoda.setTime(LocalTime.of(12, 10));
    appointmentKoda.setPatient(patientKoda);
    appointmentKoda.setEmergency(true);
    appointmentKoda.setReason("caca explosiva");
    appointmentKoda.setPast(true);
    appointmentKoda.setTreatment("Aspirina");
  }

  @Test
  void test_update_appointment() {
    appointmentServices.updateAppointment(appointmentKoda, 2);

    verify(iAppointmentRepository, times(1)).save(appointmentKoda);
    assertEquals(2, appointmentKoda.getId());
  }
}
