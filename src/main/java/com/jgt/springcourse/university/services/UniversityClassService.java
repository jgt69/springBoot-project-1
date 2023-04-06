package com.jgt.springcourse.university.services;

import com.jgt.springcourse.exception.ResourceNotFoundException;
import com.jgt.springcourse.university.helpers.UniversityClassDto;
import com.jgt.springcourse.university.helpers.UniversityClassRequest;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UniversityClassService {
    List<UniversityClassDto> getAllUniversityClasses(Pageable pageable);

    UniversityClassDto getUniversityClassById(Long universityClassId) throws ResourceNotFoundException;

    // Create method
    void createUniversityClass(@Valid UniversityClassRequest universityClassRequest);

    // Update method
    void updateUniversityClass(Long universityClassId, @Valid UniversityClassRequest universityClassRequest) throws
            ResourceNotFoundException;

    // Delete method
    void deleteUniversityClass(Long universityClassId) throws ResourceNotFoundException;
}
