package com.repository;

import com.enums.Role;
import com.exception.UserNotFoundException;
import com.model.User;
import com.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository {

    DBConnection dbConnection = DBConnection.getInstance();
    public UserRepository() {
        System.out.println("UserRepo: " + dbConnection);
    }

    public User authenticateUser(String username, String password)
            throws SQLException, UserNotFoundException {

        Connection connection = dbConnection.dbConnect();

        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";

        PreparedStatement preparedStatement =
                connection.prepareStatement(sql);

        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);

        ResultSet rst = preparedStatement.executeQuery();

        if (rst.next()) {
            int id = rst.getInt("id");

            String dbUsername = rst.getString("username");

            String dbPassword = rst.getString("password");

            Role role =
                    Role.valueOf(
                            rst.getString("role").toUpperCase()
                    );

            User user = new User(
                    id,
                    dbUsername,
                    dbPassword,
                    role
            );

            dbConnection.dbClose();
            return user;
        } else {
            dbConnection.dbClose();
            throw new UserNotFoundException("Invalid Credentials");
        }
    }
}