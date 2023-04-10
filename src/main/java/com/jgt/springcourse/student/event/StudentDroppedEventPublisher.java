package com.jgt.springcourse.student.event;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component @RequiredArgsConstructor
public class StudentDroppedEventPublisher {
    // We use ApplicationEventPublisher a built-in Spring function
    private final ApplicationEventPublisher eventPublisher;
    // Now we create methods to publish events within this class
    public void publishEvent(Integer courseId, Integer studentId){
        var event = new StudentDroppedEvent(courseId, studentId);
        eventPublisher.publishEvent(event);
    }
}
