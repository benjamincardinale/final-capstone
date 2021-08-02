package com.techelevator.controller;

import com.techelevator.dao.JdbcPetDao;
import com.techelevator.model.Pet;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
}
