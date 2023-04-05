package com.jgt.springcourse.student.service;

import com.jgt.springcourse.exception.ResourceNotFoundException;
import com.jgt.springcourse.student.helpers.StudentDto;
import com.jgt.springcourse.student.helpers.StudentRequest;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StudentService {

    Page<StudentDto> getAllStudents (Pageable pageable);

    StudentDto getStudentById(Long studentId) throws ResourceNotFoundException;

    void createStudent(@Valid StudentRequest studentRequest);
    void updateStudent(Long studentId, @Valid StudentRequest studentRequest) throws ResourceNotFoundException;
    void deleteStudent(Long studentId) throws ResourceNotFoundException;


}
