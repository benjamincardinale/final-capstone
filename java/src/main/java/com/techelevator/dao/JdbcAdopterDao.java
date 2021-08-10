package com.techelevator.dao;

import com.techelevator.model.Adopter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

@Service
public class JdbcAdopterDao implements AdopterDao{
    private JdbcTemplate jdbcTemplate;

     public JdbcAdopterDao (JdbcTemplate jdbcTemplate) {
         this.jdbcTemplate = jdbcTemplate;
     }

    @Override
    public Adopter getAdopterById(long id) {
         String sql = "SELECT adoption_id, pet_id,adopter_name,adoption_date " +
                 "FROM adoption_information WHERE adoption_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, id);
        if (result.next()) {
            return mapRowToAdopter(result);
        }
        return null;
    }

    @Override
    public Adopter insertAdopter(Adopter adopter) {
        String sql = "INSERT INTO adoption_information (pet_id, adopter_name, adoption_date) " +
                "VALUES (?,?,?) RETURNING pet_id;";
        long newId = jdbcTemplate.queryForObject(sql, Long.class, adopter.getPetId(), adopter.getName(),
                adopter.getAdoptionDate());
        return getAdopterById(newId);
    }

    private Adopter mapRowToAdopter(SqlRowSet row) {
         Adopter newAdopter = new Adopter();
         newAdopter.setAdopterId(row.getLong("adopter_id"));
         newAdopter.setPetId(row.getLong("pet_id"));
         newAdopter.setName(row.getString("adopter_name"));
         newAdopter.setAdoptionDate(row.getDate("adoption_date").toString()); //TODO fix dates or something idk
         return newAdopter;
    }
}
