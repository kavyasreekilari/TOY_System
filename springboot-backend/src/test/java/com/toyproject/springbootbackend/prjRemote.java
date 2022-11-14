package com.toyproject.springbootbackend;

import com.toyproject.springbootbackend.controller.ActivityScheduleController;
import com.toyproject.springbootbackend.controller.ChildController;
import com.toyproject.springbootbackend.model.ActivitySchedule;
import com.toyproject.springbootbackend.model.Child;
import com.toyproject.springbootbackend.model.SeniorCitizen;
import com.toyproject.springbootbackend.repository.ActivityScheduleRepository;
import com.toyproject.springbootbackend.repository.ChildRepository;
import com.toyproject.springbootbackend.repository.SeniorCitizenRepository;
import com.toyproject.springbootbackend.service.ActivityScheduleService;
import com.toyproject.springbootbackend.service.ActivityScheduleServiceImpl;
import com.toyproject.springbootbackend.service.ChildService;
import com.toyproject.springbootbackend.service.SeniorCitizenService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.Thread.sleep;
import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class prjRemote {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private SeniorCitizenRepository repo;

    @Autowired
    private ChildRepository childRepo;

//    @Autowired
//    private ChildService childService;

//    @Autowired
//    private SeniorCitizenService seniorService1;
//
    @Autowired
    private ActivityScheduleRepository activityRepo;

//    @Autowired
//    private ActivityScheduleServiceImpl activityImpl;

    SeniorCitizen senior = new SeniorCitizen();
    ChildController childControl1 = new ChildController();
    Child testChild1 = new Child();
    ActivityScheduleController activityControl = new ActivityScheduleController();


    @Test   // Performs Registration for a Senior
    public void testSeniorRegistration() {

        senior.setFirstName("Jacob");
        senior.setLastName("Hall");
        senior.setAge(78);
        senior.setContact("4475568998");
        senior.setEmail("hall48@gmail.com");
        senior.setEspionage(false);
        senior.setFelony(false);
        senior.setMisdemeanor(false);
        senior.setSoliciting(true);
        senior.setOffence(true);

        SeniorCitizen addedSenior = repo.save(senior);
        SeniorCitizen existUser = entityManager.find(SeniorCitizen.class, addedSenior.getID());
        System.out.println();
        System.out.println("Senior Registration succesful for: "+addedSenior.getFirstName()+addedSenior.getLastName());
        System.out.println();
        assertThat(senior.getEmail()).isEqualTo(existUser.getEmail());
    }

    @Test  // Performs Registration for a Child
    public void testChildRegistration() {

        testChild1.setFirstName("Ben");
        testChild1.setLastName("Jones");
        testChild1.setAge(7);
        testChild1.setContact("7797786464");
        testChild1.setEmail("jones6@outlook.com");

        Child addedChild = childRepo.save(testChild1);
        Child existChild = entityManager.find(Child.class, addedChild.getID());

        System.out.println();
        System.out.println("Child Registration succesful for: "+addedChild.getFirstName()+addedChild.getLastName());
        System.out.println();
        assertThat(testChild1.getEmail()).isEqualTo(existChild.getEmail());
    }


    @Test
    public void testActivitySupercomponent() throws InterruptedException {
        ActivitySchedule schedule1 = new ActivitySchedule();

        Child testChild2 = childRepo.findByEmail("jones6@outlook.com");
        SeniorCitizen testSenior2 = repo.findByEmail("hall48@gmail.com");

        schedule1.setChildName(testChild2.getFirstName()+testChild2.getLastName());
        schedule1.setSeniorName(testSenior2.getFirstName()+testSenior2.getLastName());
        schedule1.setDate("2022-11-13");
        schedule1.setName("Book Reading");

        activityRepo.setConfirmation("confirmed", schedule1.getActivityschedule_id());

        ActivitySchedule addedSchedule = activityRepo.save(schedule1);
        ActivitySchedule existActivity = entityManager.find(ActivitySchedule.class, schedule1.getActivityschedule_id());


        System.out.println();
        System.out.println("Activity Registration succesful for: "+schedule1.getActivityschedule_id());
        System.out.println();

        SimpleDateFormat localDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String teststarttime = localDateFormat.format( new Date());
        schedule1.setStartTime(teststarttime);
        System.out.println("Schedule start time is: "+schedule1.getStartTime());

        sleep(8000);

        String testendtime = localDateFormat.format( new Date());
        schedule1.setEndTime(testendtime);

        System.out.println("Schedule is ended for "+schedule1.getActivityschedule_id()+" at "+schedule1.getEndTime());


    }

}
