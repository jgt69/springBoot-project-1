package com.jgt.springcourse.university;


import com.jgt.springcourse.university.UniversityClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UniversityClassRepository extends JpaRepository<UniversityClass, Long> {
    Optional<UniversityClass> findByCourseId(Integer courseId);
}
