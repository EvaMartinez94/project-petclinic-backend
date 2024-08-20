package com.veterinaryClinic.services;

import com.veterinaryClinic.models.Appointment;
import com.veterinaryClinic.models.Patient;
import com.veterinaryClinic.repositories.IAppointmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;

public class AppointmentServicesTest {

  @Mock private IAppointmentRepository iAppointmentRepository;
  @InjectMocks private AppointmentServices appointmentServices;

  private Appointment appointmentDuque;
  private Appointment appointmentKoda;
  private List<Appointment> appointmentList = new ArrayList<>();

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
    appointmentDuque.setReason("stomach ache");
    appointmentDuque.setPast(false);
    appointmentDuque.setTreatment("ibuprofen");

    appointmentKoda = new Appointment();
    appointmentKoda.setId(2);
    appointmentKoda.setDate(LocalDate.of(2024, 04, 29));
    appointmentKoda.setTime(LocalTime.of(12, 10));
    appointmentKoda.setPatient(patientKoda);
    appointmentKoda.setEmergency(true);
    appointmentKoda.setReason("loose poop");
    appointmentKoda.setPast(true);
    appointmentKoda.setTreatment("aspirin");

    appointmentList.add(appointmentDuque);
    appointmentList.add(appointmentKoda);
  }
  @Test
  public void createAppointment(){
    when(iAppointmentRepository.save(ArgumentMatchers.any(Appointment.class))).thenReturn(appointmentDuque);
    Appointment newAppointment = appointmentServices.createAppointment(appointmentDuque);

    assertNotNull(newAppointment);
    assertEquals(1, newAppointment.getId());
    assertEquals(LocalDate.of(2024, 10, 10), newAppointment.getDate());
    assertEquals(LocalTime.of(19, 30), newAppointment.getTime());
    assertEquals("Duque", newAppointment.getPatient().getName());
    assertEquals(false, newAppointment.isEmergency());
    assertEquals("stomach ache", newAppointment.getReason());
    assertEquals(false, newAppointment.isPast());
    assertEquals("ibuprofen", newAppointment.getTreatment());
  }

  @Test
  public void getAllAppointment() {
    when(iAppointmentRepository.findAll()).thenReturn(appointmentList);
    List<Appointment> allAppointments = appointmentServices.getAllAppointment();
    assertEquals(2, allAppointments.size());
    assertEquals("Duque", allAppointments.get(0).getPatient().getName());
    assertEquals("Koda", allAppointments.get(1).getPatient().getName());
  }

  @Test
  public void getAppointmentId(){
when(iAppointmentRepository.findById(0)).thenReturn(Optional.of(appointmentDuque));
Optional<Appointment> appointmentId = appointmentServices.getAppointmentId(0);
assertEquals("Duque", appointmentId.get().getPatient().getName());
  }

  @Test
  void test_if_deleteAppointment_delete_the_object() {
    when(iAppointmentRepository.findById(2)).thenReturn(Optional.of(appointmentKoda));

    appointmentServices.deleteAppointment(2);

    verify(iAppointmentRepository, times(1)).deleteById(2);
  }

  @Test
  void test_if_updateAppointment_updates_the_object() {
    when(iAppointmentRepository.save(any(Appointment.class))).thenReturn(appointmentKoda);

    Appointment result = appointmentKoda;

    appointmentServices.updateAppointment(result, 2);

    assertEquals(2, result.getId());
    assertEquals(LocalDate.of(2024, 04, 29), result.getDate());
    assertEquals(LocalTime.of(12, 10), result.getTime());
    assertEquals("Koda", result.getPatient().getName());
    assertEquals(true, result.isEmergency());
    assertEquals("loose poop", result.getReason());
    assertEquals(true, result.isPast());
    assertEquals("aspirin", result.getTreatment());

    verify(iAppointmentRepository, times(1)).save(result);
  }
}
