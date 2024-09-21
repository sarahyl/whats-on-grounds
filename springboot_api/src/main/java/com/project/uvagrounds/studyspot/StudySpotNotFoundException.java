package com.project.uvagrounds.studyspot;

class StudySpotNotFoundException extends RuntimeException {

    StudySpotNotFoundException(Long id) {
      super("Could not find study spot " + id);
    }
  }