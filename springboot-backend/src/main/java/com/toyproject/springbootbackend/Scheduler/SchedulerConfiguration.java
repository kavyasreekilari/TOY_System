package com.toyproject.springbootbackend.Scheduler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;
import java.util.Calendar;

@Configuration
@EnableScheduling
public class SchedulerConfiguration {

//    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
//
//    @Scheduled(fixedRate = 5000)
//    public void scheduleByFixedRate() throws Exception {
//        System.out.println("Scheduler is executing "+ format.format(Calendar.getInstance().getTime())+"\n");
//    }
}
