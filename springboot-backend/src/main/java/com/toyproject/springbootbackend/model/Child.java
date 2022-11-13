package com.toyproject.springbootbackend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "child")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Child {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "contact")
    private String contact;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "age")
    private Integer age;

//    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinTable(
//            name = "children_roles",
//            joinColumns = { @JoinColumn(name = "child_id", referencedColumnName = "id") },
//            inverseJoinColumns = { @JoinColumn(name = "role_id", referencedColumnName = "id") }
//    )
//    private List<Role> roles = new ArrayList<>();

}
