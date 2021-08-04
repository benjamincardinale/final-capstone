package com.techelevator.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class Pet {

    private long id;
    @NotBlank(message = "Name must not be blank.")
    private String name;
    @NotBlank(message="Gender must not be blank and can be only one character.")
    private String gender;
    @NotBlank(message = "Species must not be blank.")
    private String species;
    @NotBlank(message = "Description must not be blank.")
    private String description;
    @Min(value=0, message = "Age in months must be some number that is at least 0.")
    private int age;
    private String imageUrl;
    private String imageDescription;

    public Pet() {}

    public Pet(long id, String name, String gender, String species, String description, int age, String url, String imageDescription) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.species = species;
        this.description = description;
        this.age = age;
        this.imageUrl = url;
        this.imageDescription = imageDescription;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageDescription() {
        return imageDescription;
    }

    public void setImageDescription(String imageDescription) {
        this.imageDescription = imageDescription;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", species='" + species + '\'' +
                ", description='" + description + '\'' +
                ", age=" + age +
                '}';
    }
}
