package com.toyproject.springbootbackend;

import com.toyproject.springbootbackend.model.SeniorCitizen;
import com.toyproject.springbootbackend.repository.SeniorCitizenRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class SeniorCitizenRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private SeniorCitizenRepository repo;

    @Test
    public void testSeniorRegistration() {
        SeniorCitizen senior = new SeniorCitizen();
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
}
