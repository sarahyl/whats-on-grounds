package com.project.uvagrounds;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.project.uvagrounds.models.StudySpot;
import com.project.uvagrounds.models.StudySpotRepository;

@Configuration
class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initDatabase(StudySpotRepository repository) {

    return args -> {
      log.info("Preloading " + repository.save(new StudySpot("Shannon Library", "Shannon Library description", null, null, "160 McCormick Rd", "Charlottesville", "Shannon Library", "38.0365° N, 78.5054° W")));
      log.info("Preloading " + repository.save(new StudySpot("Clemons Library", "Clem description", null, null, "164 McCormick Rd", "Charlottesville", "Clemson Library", "38.0364° N, 78.5061° W")));
    };
  }
}