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
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Optional;

import static javax.swing.UIManager.get;
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
  private ArrayList<Appointment> appointmentList = new ArrayList<>();

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

    appointmentList.add(appointmentDuque);
    appointmentList.add(appointmentKoda);
  }

  @Test
  void createAppointment() throws Exception {
    when(appointmentServices.createAppointment(any(Appointment.class)))
        .thenReturn(appointmentDuque);
    String appointmentJson =
        "{\"id\": 1,\n"
            + "        \"date\": \"10-10-2024\",\n"
            + "        \"time\": \"19:30\",\n"
            + "        \"emergency\": false,\n"
            + "        \"reason\": \"lele pancha\",\n"
            + "        \"past\": false,\n"
            + "        \"treatment\": \"Ibuprofeno\"}";
    mockMvc
        .perform(
            post("/api/vc/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(appointmentJson))
        .andExpect(status().isOk())
        .andExpect(
            content()
                .json(
                    "{\"id\": 1,\n"
                        + "        \"date\": \"10-10-2024\",\n"
                        + "        \"time\": \"19:30\",\n"
                        + "        \"emergency\": false,\n"
                        + "        \"reason\": \"lele pancha\",\n"
                        + "        \"past\": false,\n"
                        + "        \"treatment\": \"Ibuprofeno\"}"));
  }

@Test
    void getAllAppointments() throws Exception{
        when(appointmentServices.getAllAppointment()).thenReturn(appointmentList);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/vc/appointment"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\": 1,\n" +
                        "        \"date\": \"10-10-2024\",\n" +
                        "        \"time\": \"19:30\",\n" +
                        "        \"emergency\": false,\n" +
                        "        \"reason\": \"lele pancha\",\n" +
                        "        \"past\": false,\n" +
                        "        \"treatment\": \"Ibuprofeno\"},{\"id\": 2,\n" +
                        "        \"date\": \"29-04-2024\",\n" +
                        "        \"time\": \"12:10\",\n" +
                        "        \"emergency\": true,\n" +
                        "        \"reason\": \"caca explosiva\",\n" +
                        "        \"past\": true,\n" +
                        "        \"treatment\": \"Aspirina\"}]"));
}
@Test
  void getAppointmentId() throws Exception {
    when(appointmentServices.getAppointmentId(2)).thenReturn(Optional.ofNullable(appointmentKoda));
    mockMvc.perform(MockMvcRequestBuilders.get("/api/vc/appointment/2"))
            .andExpect(status().isOk())
            .andExpect(content().json("{\"id\": 2,\n" +
                    "                        \"date\": \"29-04-2024\",\n" +
                    "                        \"time\": \"12:10\",\n" +
                    "                        \"emergency\": true,\n" +
                    "                        \"reason\": \"caca explosiva\",\n" +
                    "                        \"past\": true,\n" +
                    "                        \"treatment\": \"Aspirina\"}"));
}
   }

