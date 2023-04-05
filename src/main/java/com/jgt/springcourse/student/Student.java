package com.jgt.springcourse.student;

import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.GenerationType.IDENTITY;

//Add some annotations for Lombock
@Getter @Setter @Builder
@AllArgsConstructor @NoArgsConstructor

@Entity @Table(name = "students")
public class Student {


//    Adding ID and Generatedvalue atributes to manage the primary key for our entity
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

//    Adding columns
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer studentId;
}
