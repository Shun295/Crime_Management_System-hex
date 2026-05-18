package com.repository;

import com.enums.IncidentType;
import com.enums.Status;
import com.model.Incident;
import com.model.Officer;
import com.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IncidentRepository {

    DBConnection dbConnection = DBConnection.getInstance();
    public IncidentRepository() {
        System.out.println("IncidentRepo: " + dbConnection);
    }

    // View incidents for logged-in officer
    public List<Incident> viewIncident(int userId) throws SQLException {
        List<Incident> list = new ArrayList<>();

        Connection connection = dbConnection.dbConnect();

        String sql = "SELECT i.id, i.type, i.progress_details, i.status " +
                "FROM incident i " +
                "JOIN officer o ON i.officer_id = o.id " +
                "WHERE o.users_id = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, userId);

        ResultSet rst = preparedStatement.executeQuery();

        while (rst.next()) {
            int id = rst.getInt("id");

            IncidentType incidentType =
                    IncidentType.valueOf(
                            rst.getString("type")
                                    .toUpperCase()
                                    .replace(" ", "_")
                    );

            String progressDetails = rst.getString("progress_details");

            Status incidentStatus =
                    Status.valueOf(
                            rst.getString("status").toUpperCase()
                    );

            Incident incident = new Incident(
                    id,
                    incidentType,
                    progressDetails,
                    incidentStatus
            );

            list.add(incident);
        }

        dbConnection.dbClose();
        return list;
    }

    // Insert incident for logged-in officer
    public void insertIncident(int userId, Incident incident) throws SQLException {
        Connection connection = dbConnection.dbConnect();

        // find officer mapped to logged-in user
        String fetchOfficerSql = "SELECT id, name FROM officer WHERE users_id = ?";

        PreparedStatement ps1 = connection.prepareStatement(fetchOfficerSql);
        ps1.setInt(1, userId);

        ResultSet rst = ps1.executeQuery();

        if (rst.next()) {
            int officerId = rst.getInt("id");
            String officerName = rst.getString("name");

            Officer officer = new Officer();
            officer.setId(officerId);
            officer.setName(officerName);

            incident.setOfficer(officer);

            String insertSql =
                    "INSERT INTO incident(officer_id, type, progress_details, status) VALUES (?, ?, ?, ?)";

            PreparedStatement ps2 = connection.prepareStatement(insertSql);

            ps2.setInt(1, incident.getOfficer().getId());

            ps2.setString(
                    2,
                    incident.getIncidentType()
                            .name()
                            .toLowerCase()
                            .replace("_", " ")
            );

            ps2.setString(3, incident.getProgressDetails());

            ps2.setString(
                    4,
                    incident.getStatus()
                            .name()
                            .toLowerCase()
            );

            ps2.executeUpdate();
        }

        dbConnection.dbClose();
    }
}