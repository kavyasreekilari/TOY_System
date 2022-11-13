package com.toyproject.springbootbackend.Scheduler;

import java.text.SimpleDateFormat;

public class SchedulerUtil {


    public void scheduleActivity(String date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dt = dateFormat.format(date);
        String startTime = dt;

    }

}
