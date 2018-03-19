package com.maggie.controller;

import com.maggie.model.Student;
import com.maggie.service.StudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class StudentControllerTest {

    @Mock
    private StudentService studentService;

    @Test
    public void shouldGetAStudentByItsId() {
        Student student = Student.builder().id(1L).build();

        when(studentService.findById(anyLong())).thenReturn(Optional.of(student));

        StudentController studentController = new StudentController(studentService);
        ResponseEntity responseEntity = studentController.findById(1L);

        verify(studentService, times(1)).findById(anyLong());

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
    }
}
