package com.ncs.springBootPractice.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController //making a class a restful service
@RequestMapping(path = "api/v1/student")
public class StudentController {

    private final StudentService _studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        _studentService = studentService;
    }

    @GetMapping //GET
    public List<Student> getStudents() {
        return _studentService.getStudents();
    }

    @PostMapping
    public void registerNewStudent(@RequestBody Student student) {
        _studentService.addNewStudent(student);
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId) {
        _studentService.deleteStudent(studentId);
    }

    @PutMapping(path = "{studentId}")
    public void updateStudent(
            @PathVariable("studentId") Long studentId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email) {
        _studentService.updateStudent(studentId, name, email);
    }

}
