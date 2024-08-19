package com.veterinaryClinic.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import static org.junit.Assert.*;

public class AppointmentModelsTest {
    private Appointment appointment;
    private Patient patient;

    @BeforeEach
    public void setUp() {
        patient = new Patient();
        patient.setIdentificationNumber(1L);
        patient.setName("Duque");

        appointment = new Appointment();
        appointment.setId(1);
        appointment.setDate(LocalDate.of(2024, 9, 15));
        appointment.setTime(LocalTime.of(10, 30));
        appointment.setEmergency(true);
        appointment.setReason("vaccination");
        appointment.setPast(false);
        appointment.setTreatment("antibiotics");
        appointment.setPatient(patient);
    }

    @Test
    public void testGettersAndSetters() {
        assertEquals(1, appointment.getId());
        assertEquals(LocalDate.of(2024, 9, 15), appointment.getDate());
        assertEquals(LocalTime.of(10, 30), appointment.getTime());
        assertTrue(appointment.isEmergency());
        assertEquals("vaccination", appointment.getReason());
        assertFalse(appointment.isPast());
        assertEquals("antibiotics", appointment.getTreatment());
        assertEquals(patient, appointment.getPatient());
    }

    @Test
    public void testPatientRelationship() {

        assertNotNull(appointment.getPatient());

        assertEquals(Long.valueOf(1), appointment.getPatient().getIdentificationNumber());
        assertEquals("Duque", appointment.getPatient().getName());
    }


    @Test
    public void testAllArgsConstructor() {
        Appointment newAppointment = new Appointment(
                2,
                LocalDate.of(2024, 10, 20),
                LocalTime.of(14, 00),
                false,
                "general consultation",
                true,
                "ibuprofen",
                patient
        );

        assertEquals(2, newAppointment.getId());
        assertEquals(LocalDate.of(2024, 10, 20), newAppointment.getDate());
        assertEquals(LocalTime.of(14, 00), newAppointment.getTime());
        assertFalse(newAppointment.isEmergency());
        assertEquals("general consultation", newAppointment.getReason());
        assertTrue(newAppointment.isPast());
        assertEquals("ibuprofen", newAppointment.getTreatment());
        assertEquals(patient, newAppointment.getPatient());
    }
}
