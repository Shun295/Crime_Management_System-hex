package com.service;

import com.enums.Status;
import com.model.Incident;
import com.repository.IncidentRepository;

import java.sql.SQLException;
import java.util.List;

public class IncidentService {

    IncidentRepository incidentRepository = new IncidentRepository();

    // Case 1 - View incidents
    public List<Incident> viewIncident(int userId) throws SQLException {
        return incidentRepository.viewIncident(userId);
    }

    // Case 2 - Filter incidents by status using Streams
    public List<Incident> filterIncidentByStatus(int userId, Status status)
            throws SQLException {

        return incidentRepository.viewIncident(userId)
                .stream()
                .filter(incident -> incident.getStatus().equals(status))
                .toList();
    }

    // Case 3 - Insert incident
    public void insertIncident(int userId, Incident incident)
            throws SQLException {

        incidentRepository.insertIncident(userId, incident);
    }
}