package com.jgt.springcourse.university.helpers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

// This is our DTO so this is what the server is sending. Send the name and courseId
@Data @Builder
@AllArgsConstructor
public class UniversityClassDto {
    private Long id;
    private String name;
    private Integer courseId;
    private String professor;
    private String description;
}
