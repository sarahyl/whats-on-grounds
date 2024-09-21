package com.project.uvagrounds.user;

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

import com.project.uvagrounds.studyspot.StudySpot;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;

@RestController
public class UserController {
    private UserRepository repository;
    private UserAssembler assembler;

    UserController(UserRepository repository, UserAssembler assembler){
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping
    CollectionModel<EntityModel<User>> all() {
      List<EntityModel<User>> users = repository.findAll().stream()
          .map(assembler::toModel)
          .collect(Collectors.toList());

      return CollectionModel.of(users, linkTo(methodOn(UserController.class).all()).withSelfRel());
    }

    @PostMapping("/users")
    ResponseEntity<?> newUser(@RequestBody User newUser){
      EntityModel<User> entityModel = assembler.toModel(repository.save(newUser));
      return ResponseEntity //
        .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
        .body(entityModel);
    }

    @GetMapping("/users/{id}")
    EntityModel<User> one(@PathVariable Long id) {

      User user = repository.findById(id) //
          .orElseThrow(() -> new UserNotFoundException(id));

      return assembler.toModel(user);
    }

    @PutMapping("/users/{id}")
    ResponseEntity<?> replaceUser(@RequestBody User newUser, @PathVariable Long id) {
      
      User updatedUser = repository.findById(id) //
        .map(user -> {
          user.setFirstName(newUser.getFirstName());
          user.setLastName(newUser.getLastName());
          user.setGradYear(newUser.getGradYear());
          user.setPictureURL(newUser.getPictureURL());
          return repository.save(user);
        }) //
        .orElseGet(() -> {
          return repository.save(newUser);
        });

      EntityModel<User> entityModel = assembler.toModel(updatedUser);

      return ResponseEntity //
          .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
          .body(entityModel);
    }
  
    @DeleteMapping("/users/{id}")
    ResponseEntity<?> deleteUser(@PathVariable Long id) {
      repository.deleteById(id);
      return ResponseEntity.noContent().build();
    }
}
