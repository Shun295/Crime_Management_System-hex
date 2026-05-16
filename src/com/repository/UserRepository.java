package com.repository;

import com.enums.IncidentType;
import com.enums.Role;
import com.enums.Status;
import com.exception.UserNotFoundException;
import com.model.Incident;
import com.model.Officer;
import com.model.User;
import com.mysql.cj.protocol.Resultset;
import com.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private final DBConnection dbConnection = new DBConnection();

    public User authenticateUser(String username, String password) throws SQLException {
        Connection connection = dbConnection.dbConnect();
        String sql = "select * from users where username=? and password=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);

        ResultSet rst = preparedStatement.executeQuery();
        if (rst.next()) {
            int id = rst.getInt("id");
            String username1 = rst.getString("username");
            String password1 = rst.getString("password");
            Role role = Role.valueOf(rst.getString("role").toUpperCase());
            User user = new User(id, username1, password1, role);
            dbConnection.dbClose();
            return user;

        } else {
            dbConnection.dbClose();
            throw new UserNotFoundException("Invalid Credentials");
        }


    }

    public List<Incident> viewIncident(int userId) throws SQLException {
        List<Incident> list = new ArrayList<>();
        Connection connection = dbConnection.dbConnect();
        String sql = "SELECT i.id, i.type, i.progress_details, i.status FROM incident i JOIN officer o ON i.officer_id = o.id WHERE o.users_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, userId);
        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            int incidentId = rs.getInt("id");
            IncidentType incidentType = IncidentType.valueOf(rs.getString("type").toUpperCase().replace(" ", "_"));
            String progressDetails = rs.getString("progress_details");
            Status status = Status.valueOf(rs.getString("status").toUpperCase());

            Incident incident = new Incident(incidentId, incidentType, progressDetails, status);
            list.add(incident);

        }
        dbConnection.dbClose();
        return list;

    }

    public List<Incident> filterIncidentByStatus(int id, Status status) throws SQLException {
        List<Incident> list = new ArrayList<>();
        Connection connection = dbConnection.dbConnect();
        String sql = "SELECT i.id, i.type, i.progress_details, i.status FROM incident i JOIN officer o ON i.officer_id = o.id WHERE o.users_id = ? AND i.status = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        preparedStatement.setString(2, status.name().toLowerCase());
        ResultSet rst = preparedStatement.executeQuery();

        while (rst.next()) {
            int userid = rst.getInt("id");
            IncidentType incidentType = IncidentType.valueOf(rst.getString("type").toUpperCase().replace(" ", "_"));
            String progressDetails = rst.getString("progress_details");
            Status incidentStatus = Status.valueOf(rst.getString("status").toUpperCase());
            Incident incident = new Incident(userid, incidentType, progressDetails, incidentStatus);
            list.add(incident);

        }
        /*when u want to include the foreign key
        int officerId = rst.getInt("officer_id");
          Officer officer = new Officer();
          officer.setId(officerId);
I         Incident incident = new Incident(id, incidentType, progressDetails, incidentStatus, officer);*/
        dbConnection.dbClose();
        return list;

    }
}