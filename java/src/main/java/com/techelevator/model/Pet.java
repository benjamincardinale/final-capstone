package com.techelevator.model;

public class Pet {

    private long id;
    private String name;
    private boolean neutered; //probably not have this
    private String gender;
    private String species;
    private String description;
    private int age;

    public Pet() {}

    public Pet(long id, String name, boolean neutered, String gender, String species, String description, int age) {
        this.id = id;
        this.name = name;
        this.neutered = neutered;
        this.gender = gender;
        this.species = species;
        this.description = description;
        this.age = age;
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

    public boolean isNeutered() {
        return neutered;
    }

    public void setNeutered(boolean neutered) {
        this.neutered = neutered;
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

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", neutered=" + neutered +
                ", gender='" + gender + '\'' +
                ", species='" + species + '\'' +
                ", description='" + description + '\'' +
                ", age=" + age +
                '}';
    }
}
