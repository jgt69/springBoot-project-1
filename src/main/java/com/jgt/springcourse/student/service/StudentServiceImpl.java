package com.jgt.springcourse.student.service;

import com.jgt.springcourse.exception.ResourceNotFoundException;
import com.jgt.springcourse.student.Student;
import com.jgt.springcourse.student.StudentRepository;
import com.jgt.springcourse.student.helpers.StudentDto;
import com.jgt.springcourse.student.helpers.StudentMapper;
import com.jgt.springcourse.student.helpers.StudentRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

import static java.util.Objects.requireNonNull;

@Service @RequiredArgsConstructor
public class StudentServiceImpl implements StudentService{

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;


    @Override
    public Page<StudentDto> getAllStudents(Pageable pageable) { // Pageable provides things such as limit size and pages

        var students = studentRepository.findAll(pageable);
        return students.map(studentMapper);
    }

    @Override
    public StudentDto getStudentById(Long studentId) throws ResourceNotFoundException {

        requireNonNull(studentId, "Student ID must not be null");

        return studentRepository.findById(studentId)
                .map(studentMapper)
                .orElseThrow(()-> new ResourceNotFoundException("Student not found for ID: " + studentId));
    }

    @Override @Transactional
    public void createStudent(@Valid StudentRequest studentRequest) {

        Student student = Student.builder()
                .name(studentRequest.name())
                .studentId(studentRequest.studentId())
                .build();
        studentRepository.save(student);
    }

    @Override @Transactional
    public void updateStudent(Long studentId, StudentRequest studentRequest) throws ResourceNotFoundException {

        requireNonNull(studentId, "Student ID must not be null");
        requireNonNull(studentRequest, "Student request must not be null");

        var student = studentRepository.findById(studentId)
                .orElseThrow(()-> new ResourceNotFoundException("Student not found for ID: " + studentId) );

        Optional.ofNullable(studentRequest.name())
                .ifPresent(student::setName);

        Optional.ofNullable(studentRequest.studentId())
                .ifPresent(student::setStudentId);

        studentRepository.save(student);

    }

    @Override @Transactional
    public void deleteStudent(Long studentId) throws ResourceNotFoundException {
        requireNonNull(studentId, "Student ID must not be null");
        var student = studentRepository.findById(studentId)
                .orElseThrow(()-> new ResourceNotFoundException("Student not found for ID: " + studentId) );
        studentRepository.delete(student);
    }
}
