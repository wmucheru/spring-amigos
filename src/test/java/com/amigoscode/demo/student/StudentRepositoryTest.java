package com.amigoscode.demo.student;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    void shouldFindStudentByEmail() {
        Student student = new Student();
        student.setName("Mickey");
        student.setEmail("mickey@example.com");
        studentRepository.save(student);

        Optional<Student> result = studentRepository.findByEmail(student.getEmail());

        assertTrue(result.isPresent());
    }

    @Test
    void shouldCheckIfStudentEmailDoesNotExist(){
        String email = "unfound@example.com";
        Optional<Student> result = studentRepository.findByEmail(email);
        assertTrue(result.isEmpty());
    }
}