package com.techelevator.controller;

import com.techelevator.dao.JdbcVolunteerDao;
import com.techelevator.model.Volunteer;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
public class UserController {
    private JdbcVolunteerDao jdbcVolunteerDao;

    public UserController(JdbcVolunteerDao jdbcVolunteerDao) {
        this.jdbcVolunteerDao = jdbcVolunteerDao;
    }

    @RequestMapping(path = "/apply", method = RequestMethod.POST)
    public Volunteer applyForVolunteer(@Valid @RequestBody Volunteer volunteer) {
        return jdbcVolunteerDao.insertVolunteer(volunteer);
    }
}
