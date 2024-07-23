package com.veterinaryClinic.models;

import com.fasterxml.jackson.annotation.JsonFormat;
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
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
        private LocalDate date;

        @Column(name = "Time", nullable = false)
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
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

