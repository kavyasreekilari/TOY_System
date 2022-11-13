package com.toyproject.springbootbackend.service;

import com.toyproject.springbootbackend.model.ActivitySchedule;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public interface ActivityScheduleService {

    public List<ActivitySchedule> listAllActivitySchedules();

    public void saveActivitySchedule(ActivitySchedule activitySchedule);

    public void delete(Integer id);

    public int setConfirmation(String confirmation, Integer id);

    public int setPrescription(String confirmation, Integer id);

    public Optional<ActivitySchedule> get(Integer id) ;

    public List<ActivitySchedule> findByChildName(String childName);

    public List<ActivitySchedule> findBySeniorName(String seniorName);

    public List<ActivitySchedule> findByDate(String date, String seniorName);

}

