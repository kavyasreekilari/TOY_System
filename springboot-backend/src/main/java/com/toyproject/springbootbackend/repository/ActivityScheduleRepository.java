package com.toyproject.springbootbackend.repository;

import com.toyproject.springbootbackend.model.ActivitySchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityScheduleRepository extends JpaRepository<ActivitySchedule, Integer> {

    List<ActivitySchedule> findBySeniorName(String seniorName);
    List<ActivitySchedule> findByChildName(String childName);

    @Modifying
    @Query(value = "update activityschedule a set a.confirmed = ?1 where a.activityschedule_id = ?2", nativeQuery=true)
    int setConfirmation(String confitmation, Integer id);


    @Modifying
    @Query(value = "update appointment a set a.prescription = ?1 where a.appointment_id = ?2", nativeQuery=true)
    int setPrescription(String prescription, Integer id);

    @Query(value="select * from appointment a where a.activity_date =?1 AND a.seniorName =?2", nativeQuery=true)
    List<ActivitySchedule> findByDate(String date, String seniorName);
}

