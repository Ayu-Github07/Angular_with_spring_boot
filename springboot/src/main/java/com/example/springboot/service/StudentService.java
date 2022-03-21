package com.example.springboot.service;

import java.util.List;

import com.example.springboot.model.Student;
import com.example.springboot.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    // Get All Studnets
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // Save the student
    public void setStudent(Student student) {
        System.out.println(student);
        studentRepository.save(student);
    }

    // Get by Id
    public Student getStudentById(int id) {
        return studentRepository.findById(id).get();
    }

    // Update
    public Student updateStudent(Student student) {
        List<Student> students = studentRepository.findAll();

        try {
            System.out.println("Id in service layer: " + student.getId());
            for (Student s : students) {
                if (s.getId() == student.getId()) {
                    System.out.println("Data: " + s);
                    studentRepository.save(student);
                }
            }
            return student;
        } catch (Exception e) {
            System.out.println("Student Not Found!!!");
            return null;
        }
    }

    // Delete
    public void deleteStudent(int id) {
        studentRepository.deleteById(id);
    }
}
