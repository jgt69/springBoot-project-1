package com.jgt.springcourse.student;

import com.jgt.springcourse.exception.ResourceNotFoundException;
import com.jgt.springcourse.student.helpers.StudentDto;
import com.jgt.springcourse.student.helpers.StudentRequest;
import com.jgt.springcourse.student.service.StudentServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// *******************************
// REST CONTROLLER CRUD OPERATIONS
// *******************************
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/students")
public class StudentController {
    private final StudentServiceImpl studentServiceImpl;

    // Get all students
    @GetMapping
    public ResponseEntity<Page<StudentDto>> getAllStudents(
            @PageableDefault(size = 20, page = 0, sort = "name")
            Pageable pageable) {
        // Equivalente a la expresión de abajo: Page<StudentDto> students = studentServiceImpl.getAllStudents(pageable);
        var students = studentServiceImpl.getAllStudents(pageable);

        return ResponseEntity.ok().body(students);
    }
    // Get student by ID
    @GetMapping("/{id") // Web browser path variable
    public ResponseEntity<StudentDto> getStudentById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        StudentDto studentDto = studentServiceImpl.getStudentById(id);

        return ResponseEntity.ok().body(studentDto);
    }
    // Post is our create operation
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createStudent(@RequestBody @Valid StudentRequest studentRequest) {
        studentServiceImpl.createStudent(studentRequest);
    }
    // Update operation
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStudent(@PathVariable(value = "id") Long id, @RequestBody @Valid StudentRequest
            studentRequest) throws ResourceNotFoundException {
        studentServiceImpl.updateStudent(id, studentRequest);
    }
    // Delete operation
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStudent(@PathVariable Long id) throws ResourceNotFoundException {
        studentServiceImpl.deleteStudent(id);
    }
}
