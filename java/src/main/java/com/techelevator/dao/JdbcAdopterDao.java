package com.techelevator.dao;

import com.techelevator.model.Adopter;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcAdopterDao implements AdopterDao{
    private JdbcTemplate jdbcTemplate;

     public JdbcAdopterDao (JdbcTemplate jdbcTemplate) {
         this.jdbcTemplate = jdbcTemplate;
     }

    @Override
    public void insertAdopter(Adopter adopter) {
        
    }
}
