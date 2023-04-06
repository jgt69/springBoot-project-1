package com.jgt.springcourse.university.services;

import com.jgt.springcourse.exception.ResourceNotFoundException;
import com.jgt.springcourse.university.UniversityClass;
import com.jgt.springcourse.university.helpers.UniversityClassDto;
import com.jgt.springcourse.university.helpers.UniversityClassMapper;
import com.jgt.springcourse.university.helpers.UniversityClassRepository;
import com.jgt.springcourse.university.helpers.UniversityClassRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static java.util.Objects.requireNonNull;

@Service
@RequiredArgsConstructor
public class UniversityClassServiceImpl implements UniversityClassService {
    private final UniversityClassRepository universityClassRepository;
    private final UniversityClassMapper universityClassMapper;

    @Override
    public List<UniversityClassDto> getAllUniversityClasses(Pageable pageable) {
        return universityClassRepository
                .findAll(pageable)
                .stream()
                .map(universityClassMapper)
                .toList();
    }

    @Override
    public UniversityClassDto getUniversityClassById(Long universityClassId) throws ResourceNotFoundException {
        requireNonNull(universityClassId, "University class ID must not be null");
        return universityClassRepository
                .findById(universityClassId)
                .map(universityClassMapper)
                .orElseThrow(() -> new ResourceNotFoundException("University class not found for this ID: " +
                        universityClassId));
    }

    @Override @Transactional
    public void createUniversityClass(@Valid UniversityClassRequest universityClassRequest) {
        var universityClass = UniversityClass.builder()
                .name(universityClassRequest.name())
                .courseId(universityClassRequest.courseId())
                .professor(universityClassRequest.professor())
                .description(universityClassRequest.description())
                .build();
        universityClassRepository.save(universityClass);
    }

    @Override @Transactional
    public void updateUniversityClass(Long universityClassId, @Valid UniversityClassRequest universityClassRequest)
            throws ResourceNotFoundException {
        requireNonNull(universityClassId, "University class ID must not be null");
        requireNonNull(universityClassRequest, "University class ID must not be blank");
        validateUniversityClassRequest(universityClassRequest);

        var universityClass = universityClassRepository.findById(universityClassId)
                .orElseThrow(() -> new ResourceNotFoundException("University class not found for this ID: "
                + universityClassId));
        updateUniversityClassRequest(universityClass, universityClassRequest);

        universityClassRepository.save(universityClass);
    }

    @Override
    public void deleteUniversityClass(Long universityClassId) throws ResourceNotFoundException {
        requireNonNull(universityClassId, "University class ID must not be null");
        var universityClass = universityClassRepository.findById(universityClassId)
                .orElseThrow(() -> new ResourceNotFoundException("University class not found for this ID: "
                        + universityClassId));
        universityClassRepository.delete(universityClass);
    }

    // Create a function that checks our University class make sure that it meets whatever constrains we set on it
    public void validateUniversityClassRequest(UniversityClassRequest universityClassRequest){
        var name = requireNonNull(universityClassRequest.name(), "Name must not be null");
        var professor = requireNonNull(universityClassRequest.professor(), "Professor must not be null");

        if(name.isBlank()){
            throw new IllegalArgumentException("Name must not be blank");
        }
        if(professor.isBlank()){
            throw new IllegalArgumentException("Professor must not be blank");
        }

    }
    // Create a function that checks if the university class field is null and update it
    private void updateUniversityClassRequest(UniversityClass universityClass, UniversityClassRequest universityClassRequest){
        updateIfNotNull(universityClassRequest::name, universityClass::setName);
        updateIfNotNull(universityClassRequest::courseId, universityClass::setCourseId);
        updateIfNotNull(universityClassRequest::professor, universityClass::setProfessor);
        updateIfNotNull(universityClassRequest::description, universityClass::setDescription);

    }
    // Function to check if a value is null and if isn't, if it's present, we can set that value
    private <T> void updateIfNotNull(Supplier<T> valueSupplier, Consumer<T> valueConsumer){
        Optional.ofNullable(valueSupplier.get()).ifPresent(valueConsumer);

    }
}
