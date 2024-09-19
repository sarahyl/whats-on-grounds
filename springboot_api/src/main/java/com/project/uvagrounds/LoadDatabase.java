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
      log.info("Preloading " + repository.save(new StudySpot("Shannon Library", "160 McCormick Rd")));
      log.info("Preloading " + repository.save(new StudySpot("Clemons Library", "164 McCormick Rd")));
    };
  }
}