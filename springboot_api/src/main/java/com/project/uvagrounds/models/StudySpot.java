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
    private String address;

    public StudySpot() {}

    public StudySpot(String name, String address){
        this.name = name;
        this.address = address;
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getAddress() {
        return this.address;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
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
