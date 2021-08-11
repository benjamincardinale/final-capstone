package com.techelevator.controller;

import com.techelevator.dao.UserDao;
import com.techelevator.dao.VolunteerDao;
import com.techelevator.model.Volunteer;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class UserController {
    private VolunteerDao volunteerDao;
    private UserDao userDao;

    
    public UserController(VolunteerDao volunteerDao, UserDao userDao) {
        this.volunteerDao = volunteerDao;
        this.userDao = userDao;
    }


    private boolean isUniqueUsername(Volunteer volunteer) {
        Stream<Volunteer> usernameStream = volunteerDao.getAllPendingAndApprovedVolunteers().stream();
        Set<String> usernameSet = usernameStream.map(Volunteer::getUsername).collect(Collectors.toSet());
        usernameSet.add("user");
        usernameSet.add("admin");
        return !usernameSet.contains(volunteer.getUsername());
    }


    @PreAuthorize("permitAll")
    @RequestMapping(path = "/apply", method = RequestMethod.POST)
    public Volunteer applyForVolunteer(@Valid @RequestBody Volunteer volunteer) {
        if (isUniqueUsername(volunteer)) {
            return volunteerDao.insertVolunteer(volunteer);
        }
        return null;
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(path = "/admin/pending", method = RequestMethod.GET)
    public List<Volunteer> getPendingVolunteers() {
        return volunteerDao.getAllPendingVolunteers();
    }


    @RequestMapping(path = "/user/directory", method = RequestMethod.GET)
    public List<Volunteer> userDirectory() {
        return volunteerDao.getAllApprovedVolunteers();
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(path = "/admin/approve", method = RequestMethod.PUT)
    public void approveVolunteer(@RequestBody long[] volunteerIds) {
        Volunteer volunteer;
        for (long volunteerId : volunteerIds) {
            volunteer = volunteerDao.getVolunteerFromId(volunteerId);
            volunteerDao.changeVolunteerApprovalStatus(volunteerId, "Approved");
            if (!userDao.create(volunteer.getUsername(), "newuser", "USER")) {
                volunteerDao.changeVolunteerApprovalStatus(volunteerId, "Pending");
            }
        }
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(path = "/admin/decline", method = RequestMethod.PUT)
    public void declineVolunteer(@RequestBody long[] volunteerIds) {
        Volunteer volunteer;
        for (long volunteerId : volunteerIds) {
            volunteer = volunteerDao.getVolunteerFromId(volunteerId);
            volunteerDao.changeVolunteerApprovalStatus(volunteerId, "Declined");
        }
    }
}
