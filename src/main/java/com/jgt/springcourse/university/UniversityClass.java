package com.jgt.springcourse.university;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.jgt.springcourse.student.Student;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Entity @Builder
@Table
public class UniversityClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Long data type is a 64-bit signed integer and can accommodate large ID, integer type has a limited range
    // UUID data type has the advantage of being universally unique
    private Long id;

    @Column(nullable = false)
    private Integer courseId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String professor;

    @Column(nullable = false)
    private String description;

    // Let spring knows that this is a two-way link and one is represents it on Json format. It'll prevent infinite loop
    @JsonManagedReference
    // Add many-to-many relationship to student class, Spring provides some annotations to let us do easily
    //@ManyToMany creates a join table in background
    // universityClasses is the ame of the field inside our student class
    //fetch type eager which tells Spring to fetch(buscar) the student whenever we get University classes
    @ManyToMany(mappedBy = "universityClasses", fetch = FetchType.EAGER)
    // This is saying that it should be a set of students, so we shouldn't have the same student in the same class twice
    // A set is an unordered collection of objects in which duplicate values cannot be stored.
    private Set<Student> students = new HashSet<>();

}
