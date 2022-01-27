package com.amigoscode.demo.student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock private StudentRepository studentRepository;
    private StudentService studentService;

    @BeforeEach
    void setUp() {
        studentService = new StudentService(studentRepository);
    }

    @Test
    void canGetStudents() {
        studentService.getStudents();
        verify(studentRepository).findAll();
    }

    @Test
    void canAddStudent() {
        Student student = new Student();
        student.setName("Todd");
        student.setEmail("todd@example.com");
        studentService.addStudent(student);

        ArgumentCaptor<Student> captor = ArgumentCaptor.forClass(Student.class);
        verify(studentRepository)
                .save(captor.capture());

        Student captured = captor.getValue();
        assertThat(captured).isEqualTo(student);
    }

    @Test
    void throwsExceptionWhenStudentEmailIsTaken() {
        Student student = new Student();
        student.setName("Todd");
        student.setEmail("todd@example.com");
        studentService.addStudent(student);

        given(studentRepository.isEmailTaken(student.getEmail()))
                .willReturn(true);

        assertThatThrownBy(()->studentService.addStudent(student))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Email " + student.getEmail() + " taken");
    }
}