package com.jgt.springcourse.roster;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jgt.springcourse.student.helpers.StudentDto;
import com.jgt.springcourse.university.helpers.UniversityClassDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

// For the class Roster we want to University classes, and we also want to list the students associated with those classes
// This is presented to the front end, or we'll use our data transfer objects
@Builder @Data @JsonInclude(NON_NULL)
public class Roster {
    UniversityClassDto universityClassDto;
    List<StudentDto> studentDtoList;

}
