package com.example.demo.student;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void itShouldCheckWhenStudentEmailExists() {
        //Given
        String email = "Pacopelopincho@minecraft.com";
        Student student = new Student(
                "Paco", email, Gender.FEMALE
        );
        underTest.save(student);
        //When
        boolean expected = underTest.selectExistsEmail(email);
        //Then
        assertThat(expected).isTrue();
    }

    @Test
    void itShouldCheckWhenStudentEmailDoesNotExists() {
        //Given
        String email = "Pacopelopincho@minecraft.com";
        //When
        boolean expected = underTest.selectExistsEmail(email);
        //Then
        assertThat(expected).isFalse();
    }
}