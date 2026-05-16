package com.service;

import com.enums.Status;
import com.model.Incident;
import com.model.User;
import com.repository.UserRepository;

import java.sql.SQLException;
import java.util.List;

public class UserService {
    private final UserRepository userRepository=new UserRepository();
    public User authenticateUser(String username, String password) throws SQLException {
        return userRepository.authenticateUser(username,password);
    }

   /* public List<Incident> viewIncident(int userId) throws SQLException {
        return userRepository.viewIncident(userId);
    }*/
    /*public List<Incident> filterIncidentByStatus(int id, Status status) throws SQLException {
        return userRepository.filterIncidentByStatus(id,status);*/
    // using streams

   public List<Incident> viewIncident(int userId) throws SQLException {
       return userRepository.viewIncident(userId)
               .stream()
               .toList();
   }


    public List<Incident> filterIncidentByStatus(int userId, Status status) throws SQLException {
        return userRepository.viewIncident(userId)
                .stream()
                .filter(incident -> incident.getStatus().equals(status))
                .toList();
    }

}
