package com.maggie.controller;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.eclipsesource.json.Json;
import com.maggie.model.Gender;
import com.maggie.model.Student;
import com.maggie.service.StudentService;
import java.time.LocalDate;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(StudentController.class)
public class StudentControllerIT {

    private static final String BASE_URL = "/students";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    StudentService studentService;

    @Test
    public void shouldFindAStudentByItsIdSucessfully() throws Exception {

        final Student john = Student.builder().id(1L).name("John").gender(Gender.MALE)
            .dateOfBirth(LocalDate.of(2001, 2, 10)).build();

        given(studentService.findById(1L)).willReturn(Optional.of(john));

        mockMvc.perform(get(BASE_URL.concat("/1")))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id", is(1)))
            .andExpect(jsonPath("$.name", is(john.getName())))
            .andExpect(jsonPath("$.gender", is(john.getGender().toString())))
            .andExpect(jsonPath("$.dateOfBirth", is(john.getDateOfBirth().toString())));
    }

    @Test
    public void shouldNotFindAStudentGivenAnInvalidId() throws Exception {

        given(studentService.findById(1L)).willReturn(Optional.empty());

        mockMvc.perform(get(BASE_URL.concat("/1")))
            .andExpect(status().isNotFound());
    }

    @Test
    public void shouldCreateANewStudentSuccessfully() throws Exception {
        final Student newStudent = Student.builder().id(1L).name("John").gender(Gender.MALE)
            .dateOfBirth(LocalDate.of(2001, 2, 10)).build();

        given(studentService.save(any(Student.class))).willReturn(newStudent);

        mockMvc.perform(post(BASE_URL)
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(Json.object()
                .add("name", newStudent.getName())
                .add("gender", newStudent.getGender().toString())
                .add("dateOfBirth", newStudent.getDateOfBirth().toString()).toString()))
            .andExpect(status().isCreated());
    }
}
