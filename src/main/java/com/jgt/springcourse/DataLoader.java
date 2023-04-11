package com.jgt.springcourse;

import com.jgt.springcourse.student.Student;
import com.jgt.springcourse.student.StudentRepository;
import com.jgt.springcourse.university.UniversityClass;
import com.jgt.springcourse.university.UniversityClassRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
@AllArgsConstructor
public class DataLoader implements CommandLineRunner {
    // Inject our repositories
    private final UniversityClassRepository universityClassRepository;
    private final StudentRepository studentRepository;


    @Override
    public void run(String... args) throws Exception {
        // Create a University class stream
        Set<UniversityClass> universityClasses =
                IntStream.rangeClosed(1, 10)
                        .mapToObj(index -> {
                            var universityClass = new UniversityClass();
                            universityClass.setName("Class " + index);
                            universityClass.setCourseId(index * 100);
                            universityClass.setProfessor("Professor " + index);
                            universityClass.setDescription("This is a class " + index);
                            return universityClass;
                        })
                        .collect(Collectors.toSet());
        universityClassRepository.saveAll(universityClasses);

        // Create a students stream
        Set<Student> students =
                IntStream.rangeClosed(1, 10)
                        .mapToObj(index -> {
                            var student = new Student();
                            student.setName("Student name " + index);
                            student.setStudentId(index * 100);
                            // Relationship between student with university class
                            student.setUniversityClasses(universityClasses);
                            return student;
                        })
                        .collect(Collectors.toSet());
        studentRepository.saveAll(students);
    }
}
