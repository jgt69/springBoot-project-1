package com.jgt.springcourse.university;


import com.jgt.springcourse.exception.ResourceNotFoundException;
import com.jgt.springcourse.student.helpers.StudentRequest;
import com.jgt.springcourse.university.helpers.UniversityClassDto;
import com.jgt.springcourse.university.helpers.UniversityClassRequest;
import com.jgt.springcourse.university.services.UniversityClassServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// *******************************
// REST CONTROLLER CRUD OPERATIONS
// *******************************
@RestController
@RequestMapping("/api/v1/university-classes")
@RequiredArgsConstructor
public class UniversityClassController {
    private final UniversityClassServiceImpl universityClassServiceImpl;

    // Get all university classes
    @GetMapping
    public ResponseEntity<List<UniversityClassDto>> getAllUniversityClasses(Pageable pageable){
        // List<UniversityClassDto> universityClassDtos --> it's equivalent to the next expression
        var universityClassDtos =
                universityClassServiceImpl.getAllUniversityClasses(pageable);
        return ResponseEntity.ok(universityClassDtos);
    }

    // Get university classes by ID
    @GetMapping("/{id}")
    public ResponseEntity<UniversityClassDto> getUniversityClassById(
            @PathVariable(value = "id") Long universityClassId) throws ResourceNotFoundException {
        var universityClass = universityClassServiceImpl.getUniversityClassById(universityClassId);
        return ResponseEntity.ok(universityClass);
    }

    // Post is our create operation
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createUniversityClass (@Valid @RequestBody UniversityClassRequest universityClassRequest){
        universityClassServiceImpl.createUniversityClass(universityClassRequest);
    }

    // Update operation
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUniversityClass(@PathVariable(value = "id") Long id, @RequestBody @Valid UniversityClassRequest
            universityClassRequest) throws ResourceNotFoundException {
       universityClassServiceImpl.updateUniversityClass(id, universityClassRequest);
    }

    // Delete operation
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUniversityClass(@PathVariable Long id) throws ResourceNotFoundException {
        universityClassServiceImpl.deleteUniversityClass(id);
    }

}