package com.project.uvagrounds.controllers;

class StudySpotNotFoundException extends RuntimeException {

    StudySpotNotFoundException(Long id) {
      super("Could not find study spot " + id);
    }
  }