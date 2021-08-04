package com.techelevator.controller;

import com.techelevator.dao.JdbcPetDao;
import com.techelevator.model.Pet;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
public class PetController {
    private JdbcPetDao jdbcPetDao;

    public PetController(JdbcPetDao jdbcPetDao) {
        this.jdbcPetDao = jdbcPetDao;
    }


    @RequestMapping(path="/", method = RequestMethod.GET)
    public List<Pet> getAllPets(){
        return this.jdbcPetDao.getAllPets();
    }

    @RequestMapping(path = "/add/pet", method = RequestMethod.POST)
    public Pet addPet(@Valid @RequestBody Pet pet) {
        return jdbcPetDao.addPet(pet);
    }
}
