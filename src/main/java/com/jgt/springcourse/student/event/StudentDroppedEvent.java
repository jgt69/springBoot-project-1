package com.jgt.springcourse.student.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

// EVENT SPRING DISPATCHING: Models what information we want to pass inside an event.

@Getter @Setter @AllArgsConstructor
public class StudentDroppedEvent {
    private Integer courseId;
    private Integer studentId;

}

