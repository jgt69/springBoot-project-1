package com.jgt.springcourse.student.helpers;

//DTO --> Data transfer Objects

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class StudentDto {

    private Long id;
    private String name;
    private Integer studentId;

}
