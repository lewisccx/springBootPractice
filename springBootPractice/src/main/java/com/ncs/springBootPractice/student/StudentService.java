package com.ncs.springBootPractice.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
@Service
public class StudentService {

    private final StudentRepository _studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        _studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return _studentRepository.findAll();

    }

    public void addNewStudent(Student student) {
        Optional<Student> studentByEmail = _studentRepository.findStudentByEmail(student.getEmail());
        if (studentByEmail.isPresent()) {
            throw new IllegalStateException("email taken");
        }
        _studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        boolean exists = _studentRepository.existsById(studentId);
        if (!exists) {
            throw new IllegalStateException("student with id " + studentId + " does not exists");

        }
        _studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Student student = _studentRepository.findById(studentId).orElseThrow(
                () -> new IllegalStateException(
                        "student with id " + studentId + " does not exists"
                )
        );
        if (name != null && name.length() > 0 && !Objects.equals(student.getName(), name)) {
            student.setName(name);
        }
        if(email != null && email.length() > 0 && !Objects.equals(student.getEmail(),email)){
            Optional<Student> studentOptional = _studentRepository.findStudentByEmail(email);
            if(studentOptional.isPresent()){
                throw new IllegalStateException("email taken");
            }
            student.setEmail(email);
        }
    }
}
