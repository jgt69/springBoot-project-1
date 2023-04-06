package com.jgt.springcourse.university.helpers;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

// Create a University class request. It's a Java record
@Builder
public record UniversityClassRequest(
        @NotBlank(message = "Name must not be blank") String name,
        @NotNull(message = "Course ID must not be blank") Integer courseId,
        @NotBlank(message = "Professor must not be blank") String professor,
        @NotBlank(message = "Description must not be blank") String description
) {

}
