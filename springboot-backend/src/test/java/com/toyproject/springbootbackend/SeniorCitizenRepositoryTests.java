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

    // test methods go below
    @Test
    public void testCreateUser() {
        SeniorCitizen senior = new SeniorCitizen();
        senior.setFirstName("John");
        senior.setLastName("Wick");
        senior.setContact("1234567777");
        senior.setEmail("johnwick@gmail.com");
        senior.setPassword("password");

        SeniorCitizen addedSenior = repo.save(senior);

        SeniorCitizen existUser = entityManager.find(SeniorCitizen.class, addedSenior.getID());

        assertThat(senior.getEmail()).isEqualTo(existUser.getEmail());
    }
}
