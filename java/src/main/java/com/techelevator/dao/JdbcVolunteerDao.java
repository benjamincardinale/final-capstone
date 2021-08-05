package com.techelevator.dao;

import com.techelevator.model.Volunteer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

@Service
public class JdbcVolunteerDao implements VolunteerDao{
    private JdbcTemplate jdbcTemplate;

    public JdbcVolunteerDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Volunteer getVolunteerFromId(long id) {
        String sql = "SELECT volunteer_id, first_name, last_name, email_address, has_adopted_before, has_any_certifications, approval_status_id " +
                "FROM volunteers WHERE volunteer_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, id);
        if (result.next()) {
            return mapRowToVolunteer(result);
        }
        return null;
    }

    @Override
    public Volunteer insertVolunteer(Volunteer volunteer) {
        String sql = "INSERT INTO volunteers (first_name, last_name, email_address, has_adopted_before, has_any_certifications, approval_status_id) " +
                "VALUES (?, ?, ?, ?, ?, " +
                "(SELECT approval_status_id FROM approval_statuses WHERE approval_status_description = 'Pending')" +
                ") " +
                "RETURNING volunteer_id;";
        long new_id = jdbcTemplate.queryForObject(sql, Long.class, volunteer.getFirstName(), volunteer.getLastName(),
                volunteer.getEmailAddress(), volunteer.isAdoptedBefore(), volunteer.isHasCertifications());
        return getVolunteerFromId(new_id);
    }

    @Override
    public void changeVolunteerApprovalStatus(long id, String statusStr) {
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
        newVolunteer.setEmailAddress(row.getString("email_address"));
        newVolunteer.setStatus(row.getInt("approval_status_id"));
        newVolunteer.setAdoptedBefore(row.getBoolean("has_adopted_before"));
        newVolunteer.setHasCertifications(row.getBoolean("has_any_certifications"));
        return newVolunteer;
    }
}
