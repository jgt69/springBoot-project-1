package com.jgt.springcourse.roster;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/roster")
@RequiredArgsConstructor
public class RosterController {
    private final RosterService rosterService;

    @GetMapping
    public ResponseEntity<Page<Roster>> getAllRoster(@PageableDefault(size = 10, page = 0) Pageable pageable){
        var rosters = rosterService.getAllStudentsByUniversityClass(pageable);
        return ResponseEntity.ok().body(rosters);
    }
    @DeleteMapping("/{courseId}/{studentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void dropStudentFromUniversityClass(
            @PathVariable(value = "courseId") Integer courseId,
            @PathVariable(value = "studentId") Integer studentId
    ) {
        rosterService.dropStudentFromUniversityClass(courseId, studentId);
    }
}
