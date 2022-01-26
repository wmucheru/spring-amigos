package com.amigoscode.demo.student;

import java.time.LocalDate;
import java.time.Period;

import org.apache.tomcat.jni.Local;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    @Test
    void dateOfBirthShouldCalculateAge(){
        LocalDate dob = LocalDate.of(2000, 1, 1);
        LocalDate today = LocalDate.of(2022, 1, 1);
        int age = Period.between(dob, today).getYears();

        Student student = new Student();
        student.setDob(dob);

        assertEquals(age, student.getAge());
    }
}