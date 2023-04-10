package com.jgt.springcourse.student.event;


import com.jgt.springcourse.exception.ResourceNotFoundException;
import com.jgt.springcourse.student.StudentRepository;
import com.jgt.springcourse.university.UniversityClassRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.lang.module.ResolutionException;

//The event listener component. We want to do a few things, send an email and update our repositories, because a
// student will be dropping from a class
@Component
@RequiredArgsConstructor
@Slf4j // In our server logs we can see anytime this event happens
public class StudentDroppedEventListener {
    private final JavaMailSender javaMailSender;
    private final StudentRepository studentRepository;
    private final UniversityClassRepository universityClassRepository;

    // Create a method to handle our dropped events
    @Transactional // We use this method because dealing with databases
    @EventListener
    @Async // Annotation that marks a method as a candidate for asynchronous execution.
    public void handleStudentDroppedEvent(StudentDroppedEvent event) throws ResourceNotFoundException {
      var courseId = event.getCourseId();
      var studentId = event.getStudentId();

      var universityClass = universityClassRepository.findByCourseId(courseId)
              .orElseThrow(()-> new ResourceNotFoundException("University class not found for this ID: " + courseId));
      var student = studentRepository.findByStudentId(studentId)
              .orElseThrow(()-> new ResourceNotFoundException("Student not found for this ID: " + studentId));

        // Start removing the student from the classes and save
        universityClass.getStudents().remove(student);
        universityClassRepository.save(universityClass);
        // Now remove the relationship for this student and save
        student.getUniversityClasses().remove(universityClass);
        studentRepository.save(student);

        // Sending email when student has been dropped
        var recipientEmail = "tutmotsis69@gmail.com";
        var subject = " Student Dropped";
        var message = String.format("Student %s has been dropped from %s class", student.getName(), universityClass.getName());

        try {
            sendEmail(recipientEmail, subject, message);
        } catch (MailException e){
            log.error("Send mail error: {}", e.getMessage());
        }



    }
    // SEND EMAILS FUNCTION
    @Async // Annotation that marks a method as a candidate for asynchronous execution.
    void sendEmail(String recipientEmail, String subject, String message){
        var mail = new SimpleMailMessage();
        mail.setTo(recipientEmail);
        mail.setSubject(subject);
        mail.setText(message);
        javaMailSender.send(mail);
    }
}
