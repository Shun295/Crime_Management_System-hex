package com.model;

import com.enums.IncidentType;
import com.enums.Status;

public class Incident {
    private int id;
    private IncidentType incidentType;
    private String progressDetails;
    private Status status;

    private Officer officer;    //foreign key

    public Incident(int id, IncidentType incidentType, String progressDetails, Status status) {
        this.id = id;
        this.incidentType = incidentType;
        this.progressDetails = progressDetails;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public IncidentType getIncidentType() {
        return incidentType;
    }

    public void setIncidentType(IncidentType incidentType) {
        this.incidentType = incidentType;
    }

    public String getProgressDetails() {
        return progressDetails;
    }

    public void setProgressDetails(String progressDetails) {
        this.progressDetails = progressDetails;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Officer getOfficer() {
        return officer;
    }

    public void setOfficer(Officer officer) {
        this.officer = officer;
    }

    @Override
    public String toString() {
        return "Incident{" +
                "id=" + id +
                ", incidentType=" + incidentType +
                ", progressDetails='" + progressDetails + '\'' +
                ", status=" + status +
                '}';
    }
}
