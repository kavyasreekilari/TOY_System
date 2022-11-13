package com.toyproject.springbootbackend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String name;

//    @ManyToMany(mappedBy = "roles")
//    private List<Child> child = new ArrayList<>();

    @ManyToMany(mappedBy = "roles")
    private List<SeniorCitizen> senior = new ArrayList<>();
}