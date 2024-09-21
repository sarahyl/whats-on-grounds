package com.project.uvagrounds.models;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class StudySpot {
    private @Id
    @GeneratedValue Long id;
    private String name;
    private String description;
    private Float rating;
    private Float casualRating;
    private String address;
    private String city;
    private String building;
    private String latLong;


    public StudySpot() {}

    public StudySpot(String name, String description, Float rating, Float casualRating, String address, String city, String building, String latLong){
        this.name = name;
        this.description = description;
        this.rating = rating;
        this.casualRating = casualRating;
        this.address = address;
        this.city = city;
        this.building = building;
        this.latLong = latLong;
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public Float getRating() {
        return this.rating;
    }

    public Float getCasualRating() {
        return this.casualRating;
    }

    public String getAddress() {
        return this.address;
    }

    public String getCity() {
        return this.city;
    }

    public String getBuilding() {
        return this.building;
    }

    public String getLatLong() {
        return this.latLong;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public void setCasualRating(Float casualRating) {
        this.casualRating = casualRating;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public void setLatLong(String latLong) {
        this.latLong = latLong;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
        return true;
        if (!(o instanceof StudySpot))
        return false;
        StudySpot studyspot = (StudySpot) o;
        return Objects.equals(this.id, studyspot.id) && Objects.equals(this.name, studyspot.name)
            && Objects.equals(this.address, studyspot.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.address);
    }

    @Override
    public String toString() {
        return "Study spot{" + "id=" + this.id + ", name='" + this.name + '\'' + ", address='" + this.address + '\'' + '}';
    }
}
