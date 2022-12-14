package com.toyproject.springbootbackend.service;
import com.toyproject.springbootbackend.model.Child;
import com.toyproject.springbootbackend.model.SeniorCitizen;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SeniorCitizenService {

//    @Query(value="select * from seniorcitizen s  order by s.safetylevel DESC", nativeQuery=true)
    List<SeniorCitizen> getAllSeniorCitizens();

    SeniorCitizen saveSeniorCitizen(SeniorCitizen seniorCitizen);

    SeniorCitizen getSeniorCitizenById(Integer id);

    SeniorCitizen findSeniorByEmail(String email);

//    SeniorCitizen findSeniorByAge(Integer age);

    SeniorCitizen updateSeniorCitizen(SeniorCitizen seniorCitizen);

    void deleteSeniorCitizenById(Integer id);
}
