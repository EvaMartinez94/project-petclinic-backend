package com.veterinaryClinic.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatientDTO {
    private Long identificationNumber;
    private String name;
    private int age;
    private String race;
    private String gender;
    private String tutorName;
    private String tutorPhone;
    private String url;
}

