package com.techelevator.controller;

import com.techelevator.dao.JdbcUserDao;
import com.techelevator.dao.JdbcVolunteerDao;
import com.techelevator.model.User;
import com.techelevator.model.Volunteer;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class UserController {
    private JdbcVolunteerDao jdbcVolunteerDao;
    private JdbcUserDao jdbcUserDao;

    public UserController(JdbcVolunteerDao jdbcVolunteerDao, JdbcUserDao jdbcUserDao) {
        this.jdbcVolunteerDao = jdbcVolunteerDao;
        this.jdbcUserDao = jdbcUserDao;
    }

    //@ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("permitAll")
    @RequestMapping(path = "/apply", method = RequestMethod.POST)
    public Volunteer applyForVolunteer(@Valid @RequestBody Volunteer volunteer) {
        return jdbcVolunteerDao.insertVolunteer(volunteer);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')") //Unsure if it should be this or just 'ADMIN', so I will check a previous project later and make sure.
    @RequestMapping(path = "/admin/approve/{volunteerId}", method = RequestMethod.PUT)
    public Boolean approveVolunteer(@PathVariable long volunteerId) {
        Volunteer volunteer = jdbcVolunteerDao.getVolunteerFromId(volunteerId);
        String newUserName = volunteer.getFirstName() + "_" + volunteer.getLastName();
        jdbcVolunteerDao.changeVolunteerApprovalStatus(volunteerId, 1L);
        return jdbcUserDao.create(newUserName, "newuser", "ROLE_USER");
    }
}
