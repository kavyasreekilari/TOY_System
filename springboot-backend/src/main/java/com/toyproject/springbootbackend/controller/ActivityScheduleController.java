package com.toyproject.springbootbackend.controller;

import com.toyproject.springbootbackend.Scheduler.ScheduleDefinition;
import com.toyproject.springbootbackend.Scheduler.ScheduleDefinitionBean;
import com.toyproject.springbootbackend.Scheduler.SchedulerController;
import com.toyproject.springbootbackend.Scheduler.SchedulerUtil;
import com.toyproject.springbootbackend.model.ActivitySchedule;
import com.toyproject.springbootbackend.model.Child;
import com.toyproject.springbootbackend.model.SeniorCitizen;
import com.toyproject.springbootbackend.service.ActivityScheduleService;
import com.toyproject.springbootbackend.service.ChildService;
import com.toyproject.springbootbackend.service.SeniorCitizenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class ActivityScheduleController {

    @Autowired
    private ActivityScheduleService activityScheduleService;

    @Autowired
    private ChildService childService;

    @Autowired
    private SeniorCitizenService seniorCitizenService;

    // get all activities
    @GetMapping("/activity_schedules")
    public String getAllActivities(Model model) {
        List<ActivitySchedule> activitySchedules = activityScheduleService.listAllActivitySchedules();
        model.addAttribute("activitySchedules", activitySchedules);
        return "activity_schedules";
    }

    @GetMapping("/new_activity")
    public String createActivitySchedule(Model model) {
        ActivitySchedule activitySchedule = new ActivitySchedule();
        model.addAttribute("activitySchedule", activitySchedule);

        ChildController controller1 = new ChildController();
        List childrenNames = controller1.childrenNames(childService);
        model.addAttribute("childrenNames", childrenNames);

        SeniorCitizenController controller2 = new SeniorCitizenController();
        List seniorNames = controller2.seniorCitizenNames(seniorCitizenService);
        model.addAttribute("seniorNames", seniorNames);

        List<String> listActivities = Arrays.asList("Book Reading", "Ball Game", "Puzzle", "Origami", "Drawing");
        model.addAttribute("listActivities", listActivities);

        return "new_activity";
    }
    @PostMapping("/activity_schedule/save")
    public String addActivitySchedule(@ModelAttribute("activitySchedule") ActivitySchedule activitySchedule,
                              BindingResult result, ModelMap model
    ) {
        activitySchedule.setConfirmed("Not yet confirmed");
        activityScheduleService.saveActivitySchedule(activitySchedule);
        String ActivityScheduleId=activitySchedule.getActivityschedule_id().toString();
        String message = "ActivitySchedule was successfully booked, your id is: "+ActivityScheduleId;
        return "redirect:/admin_activity_schedules";

    }

    @GetMapping("/admin_activity_schedules")
    public String showAdminActivitySchedules(Model model) {
        List<ActivitySchedule> listActivitySchedules = activityScheduleService.listAllActivitySchedules();
        model.addAttribute("listActivitySchedules",listActivitySchedules);
        String confirmed = "confirmed";
        model.addAttribute("confirmed",confirmed);
        return "admin_activity_schedules";

    }

    @GetMapping("/confirm_schedule")
    public String showConfirmm(Model model) {
        ActivitySchedule confirmation = new ActivitySchedule();
        model.addAttribute("confirmation",confirmation);
        return "confirm_schedule";
    }

    @RequestMapping("/confirm")
    public String confirm(@ModelAttribute("activitySchedule") ActivitySchedule activitySchedule, BindingResult result, ModelMap model,
                          RedirectAttributes redirectAttributes
    ) {
        System.out.println(activitySchedule);
        //Optional<com.company.varnaa.appointment> x = service.get(appointment.getAppointment_id());
        String confirmation = "confirmed";
        Integer id = activitySchedule.getActivityschedule_id();
        activityScheduleService.setConfirmation(confirmation, id);
        System.out.println(id);
        String message = "Appointment was successfully confirmed!";
        redirectAttributes.addFlashAttribute("message", message);
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");

//        SimpleDateFormat localDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String date = localDateFormat.format( new Date());
//        SchedulerUtil schedUtil = new SchedulerUtil();
//        schedUtil.scheduleActivity(date);

        SchedulerController scheduler1 = new SchedulerController();
        ScheduleDefinition def1 = new ScheduleDefinition();
        def1.setCronExpression("10 48 20 12 11 ?");
        def1.setActionType("Print Data Task");
        def1.setData("Data to be printed");
        scheduler1.scheduleATask(def1);

        return "redirect:/admin_activity_schedules";
    }

    public Optional<ActivitySchedule> get(Integer id) {
        return activityScheduleService.get(id);
    }

    public List<ActivitySchedule> findByChildName(String childName)
    {
        return activityScheduleService.findByChildName(childName);
    }

    public List<ActivitySchedule> findBySeniorName(String seniorName)
    {
        return activityScheduleService.findBySeniorName(seniorName);
    }

    public List<ActivitySchedule> findByDate(String date, String seniorName) {
        return activityScheduleService.findByDate(date, seniorName);
    }
}
