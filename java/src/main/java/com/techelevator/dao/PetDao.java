package com.techelevator.dao;

import com.techelevator.model.Pet;

import java.util.List;

public interface PetDao {

    List<Pet> getAllPets();

    Pet getPetFromId(Long id);

    Pet addPet(Pet pet);
}
