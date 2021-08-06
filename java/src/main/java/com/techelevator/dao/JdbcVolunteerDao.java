package com.techelevator.dao;

import com.techelevator.model.Volunteer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JdbcVolunteerDao implements VolunteerDao{
    private JdbcTemplate jdbcTemplate;
    private static final String VOLUNTEER_SELECT = "SELECT volunteer_id, first_name, last_name, username, email_address, " +
            "has_adopted_before, has_any_certifications, approval_status_id FROM volunteers";
    public JdbcVolunteerDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Volunteer> getAllPendingAndApprovedVolunteers() { //TODO this will also include volunteers with an approval status of DECLINED
        List<Volunteer> pendingAndApprovedVolunteers = new ArrayList<>();
        String sql = VOLUNTEER_SELECT;
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            pendingAndApprovedVolunteers.add(mapRowToVolunteer(results));
        }
        return pendingAndApprovedVolunteers;
    }

    @Override
    public List<Volunteer> getAllPendingVolunteers() {
        List<Volunteer> pendingVolunteers = new ArrayList<>();
        String sql = VOLUNTEER_SELECT + " WHERE approval_status_id = 0;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            pendingVolunteers.add(mapRowToVolunteer(results));
        }
        return pendingVolunteers;
    }

    @Override
    public Volunteer getVolunteerFromId(long id) {
        String sql = VOLUNTEER_SELECT + " WHERE volunteer_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, id);
        if (result.next()) {
            return mapRowToVolunteer(result);
        }
        return null;
    }

    @Override
    public Volunteer insertVolunteer(Volunteer volunteer) {
        String sql = "INSERT INTO volunteers (first_name, last_name, username, email_address, has_adopted_before, " +
                "has_any_certifications, approval_status_id) " +
                "VALUES (?, ?, ?, ?, ?, ?, " +
                "(SELECT approval_status_id FROM approval_statuses WHERE approval_status_description = 'Pending')" +
                ") " +
                "RETURNING volunteer_id;";
        long new_id = jdbcTemplate.queryForObject(sql, Long.class, volunteer.getFirstName(), volunteer.getLastName(),
                volunteer.getUsername(), volunteer.getEmailAddress(), volunteer.isAdoptedBefore(), volunteer.isHasCertifications());
        return getVolunteerFromId(new_id);
    }

    @Override
    public void changeVolunteerApprovalStatus(long id, String statusStr) { //TODO look for better alternatives to ILIKE or just don't use this one at all desu
        String sql = "SELECT approval_status_id FROM approval_statuses " +
                "WHERE approval_status_description ILIKE ?;";
        long statusId = jdbcTemplate.queryForObject(sql, Long.class, statusStr);
        changeVolunteerApprovalStatus(id, statusId);
    }

    @Override
    public void changeVolunteerApprovalStatus(long id, long statusId) {
        String sql = "UPDATE volunteer SET (approval_status_id) = (?) " +
                "WHERE volunteer_id = ?;";
        jdbcTemplate.update(sql, statusId, id);
    }

    private Volunteer mapRowToVolunteer(SqlRowSet row) {
        Volunteer newVolunteer = new Volunteer();
        newVolunteer.setId(row.getLong("volunteer_id"));
        newVolunteer.setFirstName(row.getString("first_name"));
        newVolunteer.setLastName(row.getString("last_name"));
        newVolunteer.setUsername(row.getString("username"));
        newVolunteer.setEmailAddress(row.getString("email_address"));
        newVolunteer.setStatus(row.getInt("approval_status_id"));
        newVolunteer.setAdoptedBefore(row.getBoolean("has_adopted_before"));
        newVolunteer.setHasCertifications(row.getBoolean("has_any_certifications"));
        return newVolunteer;
    }
}
