package com.toyproject.springbootbackend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "seniorcitizen")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class SeniorCitizen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "contact")
    private String Contact;

    @Column(name = "email", nullable = false, unique = true, length = 45)
    private String email;

    @Column(name = "age")
    private Integer age;

    @Column(name = "espionage")
    private Boolean espionage;

    @Column(name = "felony")
    private Boolean felony;

    @Column(name = "soliciting")
    private Boolean soliciting;

    @Column(name = "misdemeanor")
    private Boolean misdemeanor;

    @Column(name = "offence")
    private Boolean offence;

    @Column(name = "safetylevel")
    private Integer safetyLevel;

}
