package com.toyproject.springbootbackend.Scheduler;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

//@Service
@Component
public class ScheduleDefinitionBean implements Runnable{

    private ScheduleDefinition scheduleDefinition;

    @Override
    public void run() {
        System.out.println("Running action: " + scheduleDefinition.getActionType());
        System.out.println("With Data: " + scheduleDefinition.getData());
    }

    public ScheduleDefinition getscheduleDefinition() {
        return scheduleDefinition;
    }

    public void setscheduleDefinition(ScheduleDefinition scheduleDefinition) {
        this.scheduleDefinition = scheduleDefinition;
    }
}
