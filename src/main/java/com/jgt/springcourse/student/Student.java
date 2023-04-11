package com.jgt.springcourse.student;

import com.jgt.springcourse.university.UniversityClass;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

import static jakarta.persistence.GenerationType.IDENTITY;

//Add some annotations for Lombock
@Getter @Setter @Builder
@AllArgsConstructor @NoArgsConstructor

@Entity @Table(name = "student")
public class Student {
//    Adding ID and Generatedvalue attributes to manage the primary key for our entity
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

//    Adding columns
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer studentId;

//    CREATE MANY-TO-MANY RELATIONSHIP
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    // Specify our join table so we can manipulate within POSTGRES
    @JoinTable(name = "student_university_class", joinColumns = {@JoinColumn(name = "student_id")},
                inverseJoinColumns = {@JoinColumn(name = "university_class_id")})
    private Set<UniversityClass> universityClasses = new HashSet<>();
}
