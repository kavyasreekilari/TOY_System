package com.toyproject.springbootbackend.repository;

import com.toyproject.springbootbackend.model.Child;
import com.toyproject.springbootbackend.model.SeniorCitizen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeniorCitizenRepository extends JpaRepository<SeniorCitizen, Integer> {

    SeniorCitizen findByEmail(String email);
    SeniorCitizen findByAge(Integer age);
}
