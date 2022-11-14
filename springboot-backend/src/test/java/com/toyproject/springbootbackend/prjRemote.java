package com.toyproject.springbootbackend;

import com.toyproject.springbootbackend.controller.ActivityScheduleController;
import com.toyproject.springbootbackend.controller.ChildController;
import com.toyproject.springbootbackend.controller.SeniorCitizenController;
import com.toyproject.springbootbackend.model.ActivitySchedule;
import com.toyproject.springbootbackend.model.Child;
import com.toyproject.springbootbackend.model.SeniorCitizen;
import com.toyproject.springbootbackend.repository.ActivityScheduleRepository;
import com.toyproject.springbootbackend.repository.ChildRepository;
import com.toyproject.springbootbackend.repository.SeniorCitizenRepository;

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

    @Autowired
    private ActivityScheduleRepository activityRepo;


    SeniorCitizen senior = new SeniorCitizen();
    Child testChild1 = new Child();

    ChildController childControl1 = new ChildController();
    SeniorCitizenController seniorControl1 = new SeniorCitizenController();
    ActivityScheduleController activityControl = new ActivityScheduleController();


    @Test   // Performs Registration for a Senior
    public void testSeniorRegistration() {

        senior.setFirstName("Keith");
        senior.setLastName("Robert");
        senior.setAge(70);
        senior.setContact("6627726262");
        senior.setEmail("keithR77@gmail.com");
        senior.setEspionage(false);
        senior.setFelony(false);
        senior.setMisdemeanor(true);
        senior.setSoliciting(true);
        senior.setOffence(false);

        seniorControl1.crimeCalculator(senior);
        SeniorCitizen addedSenior = repo.save(senior);

        SeniorCitizen existUser = entityManager.find(SeniorCitizen.class, addedSenior.getID());
        System.out.println();
        System.out.println("Senior Registration succesful for: "+addedSenior.getFirstName()+addedSenior.getLastName());
        System.out.println();
        assertThat(senior.getEmail()).isEqualTo(existUser.getEmail());
    }

    @Test  // Performs Registration for a Child
    public void testChildRegistration() {

        testChild1.setFirstName("Ana");
        testChild1.setLastName("Geller");
        testChild1.setAge(5);
        testChild1.setContact("2263374646");
        testChild1.setEmail("gellers446@outlook.com");

        Child addedChild = childRepo.save(testChild1);
        Child existChild = entityManager.find(Child.class, addedChild.getID());

        System.out.println();
        System.out.println("Child Registration succesful for: "+addedChild.getFirstName()+addedChild.getLastName());
        System.out.println();
        assertThat(testChild1.getEmail()).isEqualTo(existChild.getEmail());
    }


    @Test   //Simulates Activity Schedule Supercomponent
    public void testActivitySupercomponent() throws InterruptedException {
        ActivitySchedule schedule1 = new ActivitySchedule();

        Child testChild2 = childRepo.findByEmail("gellers446@outlook.com");
        SeniorCitizen testSenior2 = repo.findByEmail("keithR77@gmail.com");

        schedule1.setChildName(testChild2.getFirstName()+testChild2.getLastName());
        schedule1.setSeniorName(testSenior2.getFirstName()+testSenior2.getLastName());
        schedule1.setDate("2022-11-14");
        schedule1.setName("Puzzle");
        schedule1.setConfirmed("Confirmed");

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

        sleep(10000);

        String testendtime = localDateFormat.format( new Date());
        schedule1.setEndTime(testendtime);

        System.out.println("Schedule is ended for "+schedule1.getActivityschedule_id()+" at "+schedule1.getEndTime());


    }

}
