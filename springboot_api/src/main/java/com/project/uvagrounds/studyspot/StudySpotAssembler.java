package com.project.uvagrounds.studyspot;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
class StudySpotModelAssembler implements RepresentationModelAssembler<StudySpot, EntityModel<StudySpot>> {

  @Override
  public EntityModel<StudySpot> toModel(StudySpot studyspot) {

    return EntityModel.of(studyspot, //
        linkTo(methodOn(StudySpotController.class).one(studyspot.getId())).withSelfRel(),
        linkTo(methodOn(StudySpotController.class).all()).withRel("studyspots"));
  }
}
