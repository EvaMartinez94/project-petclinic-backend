package com.veterinaryClinic.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class Appointment {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)

        private int id;

        @Column(name = "Date", nullable = false)
        private LocalDate date;

        @Column(name = "Time", nullable = false)
        private LocalTime time;

        @Column(name = "Patient", nullable = false)
        private String patient;

        @Column(name = "Emergency consultation")
        private boolean emergency;

        @Column(name = "Reason for appointment")
        private String reason;

        @Column(name = "Appointment status")
        private boolean past;
    }

