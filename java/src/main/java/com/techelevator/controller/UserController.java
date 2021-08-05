package com.techelevator.controller;

import com.techelevator.dao.JdbcUserDao;
import com.techelevator.dao.JdbcVolunteerDao;
import com.techelevator.model.Volunteer;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    private boolean isUniqueUsername(Volunteer volunteer) { //Doing this map is probably a load of nonsense
        Stream<Volunteer> usernameStream = jdbcVolunteerDao.getAllPendingAndApprovedVolunteers().stream();
        Set<String> usernameSet = usernameStream.map(Volunteer::getUsername).collect(Collectors.toSet());
        usernameSet.add("user");
        usernameSet.add("admin");
        return !usernameSet.contains(volunteer.getUsername());
    }

    //@ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("permitAll")
    @RequestMapping(path = "/apply", method = RequestMethod.POST)
    public Volunteer applyForVolunteer(@Valid @RequestBody Volunteer volunteer) {
        if (isUniqueUsername(volunteer)) {
            return jdbcVolunteerDao.insertVolunteer(volunteer);
        }
        return null;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')") //Unsure if it should be this or just 'ADMIN', so I will check a previous project later and make sure.
    @RequestMapping(path = "/admin/approve/{volunteerId}", method = RequestMethod.PUT)
    public Boolean approveVolunteer(@PathVariable long volunteerId) {
        Volunteer volunteer = jdbcVolunteerDao.getVolunteerFromId(volunteerId);
        jdbcVolunteerDao.changeVolunteerApprovalStatus(volunteerId, 1L);
        if (jdbcUserDao.create(volunteer.getUsername(), "newuser", "ROLE_USER")) {
            return true;
        }
        else {
            jdbcVolunteerDao.changeVolunteerApprovalStatus(volunteerId, 0L);
            return false;
        }
    }
}
