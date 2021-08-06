package com.techelevator.dao;

import com.techelevator.model.Volunteer;

import java.util.List;

public interface VolunteerDao {
    Volunteer insertVolunteer(Volunteer volunteer);

    Volunteer getVolunteerFromId(long id);

    List<Volunteer> getAllPendingAndApprovedVolunteers();

    List<Volunteer> getAllPendingVolunteers();

    void changeVolunteerApprovalStatus(long id, String statusStr);
    void changeVolunteerApprovalStatus(long id, long statusId);
}
