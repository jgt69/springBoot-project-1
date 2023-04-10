package com.jgt.springcourse.roster;

import com.jgt.springcourse.student.StudentRepository;
import com.jgt.springcourse.student.event.StudentDroppedEventPublisher;
import com.jgt.springcourse.student.helpers.StudentDto;
import com.jgt.springcourse.university.UniversityClassRepository;
import com.jgt.springcourse.university.helpers.UniversityClassDto;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor
public class RosterService {
    private final UniversityClassRepository universityClassRepository;
    private final StudentDroppedEventPublisher studentDroppedEventPublisher;

    // Find all our students from the university class and from student class
    public Page<Roster> getAllStudentsByUniversityClass(Pageable pageable) {
        return universityClassRepository
                .findAll(pageable)
                .map(
                        universityClass -> {
                            var studentDtos = universityClass
                                    .getStudents()
                                    .stream()
                                    .map(student -> StudentDto.builder()
                                            .id(student.getId())
                                            .name(student.getName())
                                            .studentId(student.getStudentId())
                                            .build())
                                    .toList();
                            return Roster.builder()
                                    .universityClassDto(UniversityClassDto.builder()
                                            .id(universityClass.getId())
                                            .courseId(universityClass.getCourseId())
                                            .name(universityClass.getName())
                                            .professor(universityClass.getProfessor())
                                            .description(universityClass.getDescription())
                                            .build())
                                    .studentDtoList(studentDtos)
                                    .build();
                        }
                );
    }

    // Dropped students events
    public void dropStudentFromUniversityClass(Integer studentId, Integer courseId){
        // This call will trigger our event listener (se activará el detector de eventos)
        studentDroppedEventPublisher.publishEvent(courseId, studentId);


    }
}
