package com.maggie.controller;

import com.maggie.model.Student;
import com.maggie.service.StudentService;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/students")
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity findById(@PathVariable("id") Long id) {
        Optional<Student> student = studentService.findById(id);

        return student.isPresent() ? ResponseEntity.ok(student.get()) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity create(@RequestBody Student student) {
        return new ResponseEntity(studentService.save(student), HttpStatus.CREATED);
    }
}
