package com.toyproject.springbootbackend.repository;

import com.toyproject.springbootbackend.model.ActivitySchedule;
import com.toyproject.springbootbackend.model.Child;
import com.toyproject.springbootbackend.model.SeniorCitizen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeniorCitizenRepository extends JpaRepository<SeniorCitizen, Integer> {

    SeniorCitizen findByEmail(String email);
    SeniorCitizen findByAge(Integer age);

//    @Query(value="select * from activityschedule a  where a.confirmed =?1", nativeQuery=true)
//    List<ActivitySchedule> findByConfirmation(String confirmation);
}
