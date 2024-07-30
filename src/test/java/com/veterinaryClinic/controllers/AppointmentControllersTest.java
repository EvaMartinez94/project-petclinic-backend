package com.veterinaryClinic.controllers;

import com.veterinaryClinic.models.Appointment;
import com.veterinaryClinic.models.Patient;
import com.veterinaryClinic.services.AppointmentServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AppointmentControllersTest {
    @Mock private AppointmentServices appointmentServices;

    @InjectMocks private AppointmentControllers appointmentControllers;

    private MockMvc mockMvc;

    private Appointment appointmentDuque;
    private Appointment appointmentKoda;
    private ArrayList<Appointment> appointmentList =  new ArrayList<>();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(appointmentControllers).build();

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
  void createAppointment() throws Exception {
    when(appointmentServices.createAppointment(any(Appointment.class)))
        .thenReturn(appointmentDuque);
    String appointmentJson =
        "{'date': '10-10-2024', 'time': '19:30', 'patient': 'patient_id:02','emergency': false,'reason': 'lele pancha','past': false,'treatment': 'Ibuprofeno'}";
    mockMvc
        .perform(
            post("/api/vc/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(appointmentJson))
        .andExpect(status().isOk())
        .andExpect(
            content()
                .json(
                    "{'id':'1',date': '10-10-2024', 'time': '19:30', 'patient': 'patient_id:02','emergency': false,'reason': 'lele pancha','past': false,'treatment': 'Ibuprofeno'}"));
       }
   }

