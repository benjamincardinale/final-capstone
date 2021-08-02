package com.techelevator.dao;

import com.techelevator.model.Pet;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class JdbcPetDao implements PetDao{

        private JdbcTemplate jdbcTemplate;

        public JdbcPetDao(JdbcTemplate jdbcTemplate) {
            this.jdbcTemplate = jdbcTemplate;
        }

    @Override
    public List<Pet> getAllPets() {
        return null;
    }
}
