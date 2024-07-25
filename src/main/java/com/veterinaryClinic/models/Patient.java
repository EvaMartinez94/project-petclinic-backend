package com.veterinaryClinic.models;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.NaturalId;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "patient", indexes = {
    @Index(name = "idx_patient_identificationNumber", columnList = "identificationNumber"),
    @Index(name = "idx_patient_tutorName", columnList = "tutorName"),
    @Index(name = "idx_patient_identification_tutor", columnList = "identificationNumber, tutorName")
})

public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long patient_id;

    @NaturalId
    @Column(name = "identificationNumber", nullable = false, unique = true)
    private Long identificationNumber;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "age")
    private int age;

    @Column(name = "race")
    private String race;

    @Column(name = "gender")
    private String gender;

    @Column(name = "tutorName", nullable = false)
    private String tutorName;

    @Column(name = "tutorPhone", nullable = false)
    private String tutorPhone;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Appointment> appointments = new ArrayList<>();

}