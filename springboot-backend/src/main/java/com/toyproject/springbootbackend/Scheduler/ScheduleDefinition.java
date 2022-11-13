package com.toyproject.springbootbackend.Scheduler;

import lombok.Data;

@Data
public class ScheduleDefinition {
    private String cronExpression;
    private String actionType;
    private String data;
}


