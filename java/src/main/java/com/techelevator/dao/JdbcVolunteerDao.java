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
    private static final String VOLUNTEER_SELECT = "SELECT v.volunteer_id, v.first_name, v.last_name, v.username, v.email_address, " +
            "v.has_adopted_before, v.has_any_certifications, v.approval_status_id, a.approval_status_description " +
            "FROM volunteers v JOIN approval_statuses a ON v.approval_status_id = a.approval_status_id";
    public JdbcVolunteerDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Volunteer> getAllApprovedVolunteers() {
        List<Volunteer> approvedVolunteers = new ArrayList<>();
        String sql = VOLUNTEER_SELECT + " WHERE v.approval_status_id = 2;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql);
        while (result.next()) {
            approvedVolunteers.add(mapRowToVolunteer(result));
        }
        return approvedVolunteers;
    }

    @Override
    public List<Volunteer> getAllPendingAndApprovedVolunteers() { //TODO this will also include volunteers with an approval status of DECLINED
        List<Volunteer> pendingAndApprovedVolunteers = new ArrayList<>();
        String sql = VOLUNTEER_SELECT
                + " WHERE a.approval_status_description = 'Pending' OR a.approval_status_description = 'Approved';";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            pendingAndApprovedVolunteers.add(mapRowToVolunteer(results));
        }
        return pendingAndApprovedVolunteers;
    }

    @Override
    public List<Volunteer> getAllPendingVolunteers() {
        List<Volunteer> pendingVolunteers = new ArrayList<>();
        String sql = VOLUNTEER_SELECT + " WHERE a.approval_status_description = 'Pending';";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            pendingVolunteers.add(mapRowToVolunteer(results));
        }
        return pendingVolunteers;
    }

    @Override
    public Volunteer getVolunteerFromId(long id) {
        String sql = VOLUNTEER_SELECT + " WHERE v.volunteer_id = ?;";
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
        String sql = "UPDATE volunteers SET approval_status_id = ? " +
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
        newVolunteer.setStatus(row.getString("approval_status_description"));
        newVolunteer.setAdoptedBefore(row.getBoolean("has_adopted_before"));
        newVolunteer.setHasCertifications(row.getBoolean("has_any_certifications"));
        return newVolunteer;
    }
}
