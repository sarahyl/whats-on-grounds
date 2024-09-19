package com.project.uvagrounds.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.uvagrounds.models.StudySpot;
import com.project.uvagrounds.models.StudySpotRepository;

@RestController
class StudySpotController {
    private StudySpotRepository repository;

    StudySpotController(StudySpotRepository repository){
        this.repository = repository;
    }

    @GetMapping("/studyspots")
    List<StudySpot> all() {
      return repository.findAll();
    }
  
    @PostMapping("/studyspots")
    StudySpot newStudySpot(@RequestBody StudySpot newStudySpot) {
      return repository.save(newStudySpot);
    }
  
    // Single item
    
    @GetMapping("/studyspots/{id}")
    StudySpot one(@PathVariable Long id) {
      
      return repository.findById(id)
        .orElseThrow(() -> new StudySpotNotFoundException(id));
    }
  
    @PutMapping("/studyspots/{id}")
    StudySpot replaceStudySpot(@RequestBody StudySpot newStudySpot, @PathVariable Long id) {
      
      return repository.findById(id)
        .map(studyspot -> {
          studyspot.setName(newStudySpot.getName());
          studyspot.setAddress(newStudySpot.getAddress());
          return repository.save(studyspot);
        })
        .orElseGet(() -> {
          return repository.save(newStudySpot);
        });
    }
  
    @DeleteMapping("/studyspots/{id}")
    void deleteStudySpot(@PathVariable Long id) {
      repository.deleteById(id);
    }
}