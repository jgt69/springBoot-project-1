package com.jgt.springcourse.student.helpers;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record StudentRequest (
        @NotBlank(message = "Name must not be blank") String name,
        @NotNull(message = "StudentID must not be null") Integer studentId
) {


}
