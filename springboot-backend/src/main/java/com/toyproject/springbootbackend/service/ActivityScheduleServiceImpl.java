package com.toyproject.springbootbackend.service;

import com.toyproject.springbootbackend.model.ActivitySchedule;
import com.toyproject.springbootbackend.repository.ActivityScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ActivityScheduleServiceImpl implements ActivityScheduleService{

    @Autowired
    public ActivityScheduleRepository scheduleActivity;

    @Override
    public List<ActivitySchedule> listAllActivitySchedules() {
        return scheduleActivity.findAll(Sort.by(Sort.Direction.DESC, "date"));
    }

    public void saveActivitySchedule(ActivitySchedule activitySchedule) {
        scheduleActivity.save(activitySchedule);
    }

    public void delete(Integer id) {
        scheduleActivity.deleteById(id);
    }

    public int setConfirmation(String confirmation, Integer id) {
        return scheduleActivity.setConfirmation(confirmation, id);
    }

    public int setPrescription(String confirmation, Integer id) {
        return scheduleActivity.setPrescription(confirmation, id);
    }

    public Optional<ActivitySchedule> get(Integer id) {
        return scheduleActivity.findById(id);
    }

    @Override
    public List<ActivitySchedule> findByChildName(String childName) {
        return null;
    }

    public List<ActivitySchedule> findByPatientName(String childName)
    {
        return scheduleActivity.findByChildName(childName);
    }

    public List<ActivitySchedule> findBySeniorName(String seniorName)
    {
        return scheduleActivity.findBySeniorName(seniorName);
    }

    public List<ActivitySchedule> findByDate(String date, String seniorName){
        return scheduleActivity.findByDate(date, seniorName);
    }

    public List<ActivitySchedule> findByConfirmation(String confirmation)
    {
        return scheduleActivity.findByConfirmation(confirmation);
    }

    public void setStartTime(String date, Integer id) {
         scheduleActivity.setStartTime(date, id);
    }

    public void setEndTime(String date, Integer id) {
        scheduleActivity.setEndTime(date, id);
    }

}

