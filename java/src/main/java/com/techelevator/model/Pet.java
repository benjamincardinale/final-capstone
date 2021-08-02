package com.techelevator.model;

public class Pet {

    private long id;
    private String name;
    private String gender;
    private String species;
    private String description;
    private int age;

    public Pet() {}

    public Pet(long id, String name, String gender, String species, String description, int age) {
        this.id = id;
        this.name = name;
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
                ", gender='" + gender + '\'' +
                ", species='" + species + '\'' +
                ", description='" + description + '\'' +
                ", age=" + age +
                '}';
    }
}
