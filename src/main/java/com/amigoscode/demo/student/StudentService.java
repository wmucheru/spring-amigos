package com.amigoscode.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository repository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.repository = studentRepository;
    }

    public List<Student> getStudents(){
        return repository.findAll();
    }

    public void addStudent(Student student) {
        Optional<Student> studentOptional = repository.findStudentByEmail(student.getEmail());

        if(studentOptional.isPresent()){
            throw new IllegalStateException("Email already exists");
        }
        else{
            repository.save(student);
        }
    }
}
