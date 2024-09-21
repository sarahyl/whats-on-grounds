package com.project.uvagrounds.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long id;
  private String firstName;
  private String lastName;
  private Integer gradYear;
  private String pictureURL;

  protected User() {}

  public User(String firstName, String lastName, Integer gradYear, String pictureURL) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.gradYear = gradYear;
    this.pictureURL = pictureURL;
  }

  @Override
  public String toString() {
    return String.format(
        "User[id=%d, firstName='%s', lastName='%s']",
        id, firstName, lastName);
  }

  public Long getId() {
    return this.id;
  }

  public String getFirstName() {
    return this.firstName;
  }

  public String getLastName() {
    return this.lastName;
  }

  public Integer getGradYear(){
    return this.gradYear;
  }

  public String getPictureURL(){
    return this.pictureURL;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public void setGradYear(Integer gradYear){
    this.gradYear = gradYear;
  }

  public void setPictureURL(String pictureURL){
    this.pictureURL = pictureURL;
  }

}