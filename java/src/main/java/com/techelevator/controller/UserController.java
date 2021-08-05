package com.techelevator.controller;

import com.techelevator.dao.JdbcVolunteerDao;
import com.techelevator.model.User;
import com.techelevator.model.Volunteer;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class UserController {
    private JdbcVolunteerDao jdbcVolunteerDao;

    public UserController(JdbcVolunteerDao jdbcVolunteerDao) {
        this.jdbcVolunteerDao = jdbcVolunteerDao;
    }

    @PreAuthorize("permitAll")
    @RequestMapping(path = "/apply", method = RequestMethod.POST)
    public Volunteer applyForVolunteer(@Valid @RequestBody Volunteer volunteer) {
        return jdbcVolunteerDao.insertVolunteer(volunteer);
    }

    @RequestMapping(path = "/admin/approve", method = RequestMethod.PUT)
    public User approveVolunteer() { //TODO
        return null;
    }
}
