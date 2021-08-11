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
    public Pet getPetFromId(Long id) {
        String sql = "SELECT p.pet_id, p.pet_name, p.age_in_months, p.gender, p.species, p.description, " +
                "p.is_adopted, i.url, i.image_description FROM pets p LEFT JOIN images i ON p.pet_id = i.pet_id " +
                "WHERE p.pet_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, id);
        if (result.next()) {
            return mapRowToPet(result);
        }
        return null;
    }

    @Override
    public List<Pet> getAllPets() {
        List<Pet> allPets = new ArrayList<>();
        String sql = "SELECT p.pet_id, p.pet_name, p.age_in_months, p.gender, p.species, p.description, " +
                "p.is_adopted, i.url, i.image_description FROM pets p LEFT JOIN images i ON p.pet_id = i.pet_id " +
                "ORDER BY p.pet_id ASC;";
        SqlRowSet result = this.jdbcTemplate.queryForRowSet(sql);
        while (result.next()){
            allPets.add(mapRowToPet(result));
        }
        return allPets;
    }

    @Override
    public Pet addPet(Pet pet) {
        String sql = "INSERT INTO pets (pet_name, age_in_months, gender, species, description) " +
                "VALUES (?, ?, ?, ?, ?) RETURNING pet_id;";
        long newPetId = jdbcTemplate.queryForObject(sql, Long.class, pet.getName(), pet.getAge(), pet.getGender(),
                pet.getSpecies(), pet.getDescription());
        sql = "INSERT INTO images (pet_id, url, image_description) " +
                "VALUES (?, ?, ?);";
        jdbcTemplate.update(sql, newPetId, pet.getImageUrl(), pet.getImageDescription());
        return getPetFromId(newPetId);
    }

    @Override
    public void updatePet(Pet pet) {
        String sql = "UPDATE pets SET (pet_name, age_in_months, gender, species, description, is_adopted) " +
                "= (?,?,?,?,?,?) " +
                "WHERE pet_id = ?;";
        jdbcTemplate.update(sql, pet.getName(), pet.getAge(), pet.getGender(), pet.getSpecies(),
                pet.getDescription(), pet.isAdopted(), pet.getId());
        sql = "UPDATE images SET (url, image_description) = (?,?) WHERE pet_id = ?"; //This one could be problematic, this is fine if there is only one image per pet, but when there are multiple, I will have to redo this query to be more selective about which image it's updating.
        jdbcTemplate.update(sql, pet.getImageUrl(), pet.getDescription(), pet.getId());
    }

    private Pet mapRowToPet(SqlRowSet row) {
            Pet newPet = new Pet();
            newPet.setId(row.getLong("pet_id"));
            newPet.setName(row.getString("pet_name"));
            newPet.setGender(row.getString("gender"));
            newPet.setSpecies(row.getString("species"));
            newPet.setDescription(row.getString("description"));
            newPet.setAge(row.getInt("age_in_months"));
            newPet.setImageUrl(row.getString("url"));
            newPet.setImageDescription(row.getString("image_description"));
            newPet.setAdopted(row.getBoolean("is_adopted"));
            return newPet;
    }
}
