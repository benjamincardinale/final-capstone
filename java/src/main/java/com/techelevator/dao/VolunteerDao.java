package com.techelevator.dao;

import com.techelevator.model.Volunteer;

public interface VolunteerDao {
    Volunteer insertVolunteer(Volunteer volunteer);

    Volunteer getVolunteerFromId(long id);

    void changeVolunteerApprovalStatus(long id, String statusStr);
    void changeVolunteerApprovalStatus(long id, long statusId);
}
