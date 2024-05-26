package com.ross.gamis.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ross.gamis.domain.Country;
import com.ross.gamis.domain.Developer;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DeveloperRepositoryTest {
    @Autowired
    private DeveloperRepository developerRepository;

    private Developer createdDeveloper;

    @BeforeEach
    public void setupEach() {
        createdDeveloper = developerRepository.save(new Developer("My very unique Studio Developer 1", LocalDate.now(), Country.BRAZIL));
    }

    @AfterEach
    public void tearDownEach() {
        developerRepository.delete(createdDeveloper);
    }

    @Test
    public void getDevelopersByNameLikeShouldReturnDevelopersByStringAnyCase(){
        assertNotNull(developerRepository.getDevelopersByNameLike(" studio dev"));
        assertNotNull(developerRepository.getDevelopersByNameLike("VERY UNIQ"));
        assertNotNull(developerRepository.getDevelopersByNameLike("y v"));
    }

    @Test
    public void getDevelopersByNameLikeShouldReturnEmptyList(){
        assertTrue(developerRepository.getDevelopersByNameLike("non existent 1234").isEmpty());
    }
}
