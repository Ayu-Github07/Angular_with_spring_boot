package com.example.springboot.controller;

import java.util.List;

import com.example.springboot.model.Student;
import com.example.springboot.service.SequenceGeneratorService;
import com.example.springboot.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private SequenceGeneratorService service;

    @GetMapping("/")
    public ResponseEntity<List<Student>> getAllStudents() {

        try {
            List<Student> list = studentService.getAllStudents();
            return ResponseEntity.ok().body(list);
        } catch (Exception e) {

            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable("id") int id) {

        try {
            Student student = studentService.getStudentById(id);
            return ResponseEntity.ok().body(student);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/")
    public ResponseEntity<String> setStudents(@RequestBody Student student) {

        try {
            student.setId(service.getSequenceNumber(Student.SEQUENCE_NAME));
            studentService.setStudent(student);
            return ResponseEntity.ok().body("Student Details Added Successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateStudentsById(@RequestBody Student student,
            @PathVariable("id") int id) {
        try {
            System.out.println("Id = " + id);
            Student student2 = studentService.updateStudent(student);
            return ResponseEntity.ok().body("Student Details Updated Successfully\nStudent Details are: \n" + student2);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Warning: Student wiht student number " + id + " does not exists!");
        }

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudentsById(@PathVariable("id") int id) {
        try {
            System.out.println("Id = " + id);
            studentService.deleteStudent(id);
            return ResponseEntity.ok("Student Details Deleted Successfully");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Warning: Student details with idnumber " + id + " does not exists!");
        }

    }
}
