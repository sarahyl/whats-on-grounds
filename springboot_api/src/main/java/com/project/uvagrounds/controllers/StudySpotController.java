package com.project.uvagrounds.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.catalina.connector.Response;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;

import com.project.uvagrounds.models.StudySpot;
import com.project.uvagrounds.models.StudySpotRepository;

@RestController
class StudySpotController {
    private StudySpotRepository repository;
    private final StudySpotModelAssembler assembler;

    StudySpotController(StudySpotRepository repository, StudySpotModelAssembler assembler){
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("/studyspots")
    CollectionModel<EntityModel<StudySpot>> all() {
      List<EntityModel<StudySpot>> studyspots = repository.findAll().stream()
          .map(assembler::toModel)
          .collect(Collectors.toList());

      return CollectionModel.of(studyspots, linkTo(methodOn(StudySpotController.class).all()).withSelfRel());
    }
  
    @PostMapping("/studyspots")
    ResponseEntity<?> newStudySpot(@RequestBody StudySpot newStudySpot){
      EntityModel<StudySpot> entityModel = assembler.toModel(repository.save(newStudySpot));
      return ResponseEntity //
        .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
        .body(entityModel);
    }
    
    @GetMapping("/studyspots/{id}")
    EntityModel<StudySpot> one(@PathVariable Long id) {

      StudySpot studyspot = repository.findById(id) //
          .orElseThrow(() -> new StudySpotNotFoundException(id));

      return assembler.toModel(studyspot);
    }
  
    @PutMapping("/studyspots/{id}")
    ResponseEntity<?> replaceStudySpot(@RequestBody StudySpot newStudySpot, @PathVariable Long id) {
      
      StudySpot updatedStudySpot = repository.findById(id) //
        .map(studyspot -> {
          studyspot.setName(newStudySpot.getName());
          studyspot.setDescription(newStudySpot.getDescription());
          studyspot.setRating(newStudySpot.getRating());
          studyspot.setCasualRating(newStudySpot.getCasualRating());
          studyspot.setAddress(newStudySpot.getAddress());
          studyspot.setCity(newStudySpot.getCity());
          studyspot.setBuilding(newStudySpot.getBuilding());
          studyspot.setLatLong(newStudySpot.getLatLong());
          return repository.save(studyspot);
        }) //
        .orElseGet(() -> {
          return repository.save(newStudySpot);
        });

      EntityModel<StudySpot> entityModel = assembler.toModel(updatedStudySpot);

      return ResponseEntity //
          .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
          .body(entityModel);
    }
  
    @DeleteMapping("/studyspots/{id}")
    ResponseEntity<?> deleteStudySpot(@PathVariable Long id) {
      repository.deleteById(id);
      return ResponseEntity.noContent().build();
    }

}