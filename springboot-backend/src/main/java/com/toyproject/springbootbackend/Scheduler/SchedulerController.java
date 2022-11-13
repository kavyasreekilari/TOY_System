package com.toyproject.springbootbackend.Scheduler;


import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor;
import org.springframework.scheduling.config.ScheduledTask;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/schedule")
public class SchedulerController {

    @Autowired
    private SchedulerService schedulerService;

    @Autowired
    private ScheduleDefinitionBean scheduleDefinitionBean;

//    @PostMapping(path="/taskdef", consumes = "application/json", produces="application/json")
//    public void scheduleATask(@RequestBody ScheduleDefinition scheduleDefinition) {
    @PostMapping(path="/taskdef")
    public void scheduleATask(ScheduleDefinition scheduleDefinition) {
        scheduleDefinitionBean.setscheduleDefinition(scheduleDefinition);
        Integer id = 1;
        schedulerService.scheduleATask(id, scheduleDefinitionBean, scheduleDefinition.getCronExpression());
    }

    @GetMapping(path="/remove/{jobid}")
    public void removeJob(@PathVariable Integer jobid) {
        schedulerService.removeScheduledTask(jobid);
    }




//    private static final String SCHEDULED_TASKS = "scheduledTasks";
//
//    @Autowired
//    private ScheduledAnnotationBeanPostProcessor postProcessor;
//
//    @Autowired
//    private SchedulerConfiguration schedulerConfiguration;
//
//    @GetMapping(value = "/stop")
//    public String stopSchedule() {
//        postProcessor.postProcessBeforeDestruction(schedulerConfiguration, SCHEDULED_TASKS);
//        return "OK";
//    }
//
//    @GetMapping(value = "/start")
//    public String startSchedule() {
//        postProcessor.postProcessAfterInitialization(schedulerConfiguration, SCHEDULED_TASKS);
//        return "OK";
//    }
//
//    @GetMapping(value = "/list")
//    public String listSchedules() throws JsonProcessingException {
//        Set<ScheduledTask> setTasks = postProcessor.getScheduledTasks();
//        if (!setTasks.isEmpty()) {
//            return setTasks.toString();
//        } else {
//            return "Currently no scheduler tasks are running";
//        }
//    }
}
