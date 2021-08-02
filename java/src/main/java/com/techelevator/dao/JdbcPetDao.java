package com.techelevator.dao;

import com.techelevator.model.Pet;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JdbcPetDao implements PetDao{

        private JdbcTemplate jdbcTemplate;

        public JdbcPetDao(JdbcTemplate jdbcTemplate) {
            this.jdbcTemplate = jdbcTemplate;
        }

    @Override
    public List<Pet> getAllPets() {
        List<Pet> allPets = new ArrayList<>();
        String sql = "SELECT * from _pet_;";
        SqlRowSet result = this.jdbcTemplate.queryForRowSet(sql);
        while (result.next()){
            allPets.add(mapRowToPet(result));
        }
        return allPets;
    }

    private Pet mapRowToPet(SqlRowSet row) {
            Pet newPet = new Pet();
            newPet.setId(row.getLong("pet_id"));
            newPet.setName(row.getString("pet_name"));
            newPet.setNeutered(row.getBoolean("neutered"));
            newPet.setGender(row.getString("gender"));
            newPet.setSpecies(row.getString("species"));
            newPet.setDescription(row.getString("description"));
            newPet.setAge(row.getInt("age"));
            return newPet;
    }
}
