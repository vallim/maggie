package com.maggie.controller;

import static org.assertj.core.api.Assertions.assertThat;

import com.maggie.model.Gender;
import com.maggie.model.Student;
import com.maggie.service.StudentService;
import java.time.LocalDate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentControllerIT {

    @Autowired
    StudentController studentController;

    @Autowired
    StudentService studentService;

    @Test
    public void shouldFindAStudentByItsId() {

        final Student john = Student.builder().id(1L).name("John").gender(Gender.MALE)
            .dateOfBirth(LocalDate.of(2001, 2, 10)).build();
        studentService.save(john);

        ResponseEntity responseEntity = studentController.findById(1L);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    public void shouldNotFindAStudentGivenAnInvalidId() {
        ResponseEntity responseEntity = studentController.findById(1L);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(404);
    }
}
