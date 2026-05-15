package com.service;

import com.enums.IncidentType;
import com.enums.Status;
import com.model.Incident;

import java.util.List;

public class StreamsService {

        public List<Incident> sampleData(){
        Incident incident1 = new Incident(1, IncidentType.REPORT_ABUSE, "Case file created.", Status.INITIATED);
        Incident incident2 = new Incident(2, IncidentType.THEFT, "Security footage under review.", Status.ACTIVE);
        Incident incident3 = new Incident(3, IncidentType.MISSING_PERSON, "Statement confirmed.", Status.VERIFIED);
        Incident incident4 = new Incident(4, IncidentType.MURDER, "Documentation finalized.", Status.CLOSED);
        Incident incident5 = new Incident(5, IncidentType.THEFT, "Interviews in progress.", Status.ACTIVE);
        Incident incident6 = new Incident(6, IncidentType.MISSING_PERSON, "Initial report logged.", Status.INITIATED);
        Incident incident7 = new Incident(7, IncidentType.REPORT_ABUSE, "Evidence verified.", Status.VERIFIED);
        Incident incident8 = new Incident(8, IncidentType.MURDER, "Task force assigned.", Status.INITIATED);
        Incident incident9 = new Incident(9, IncidentType.THEFT, "Recovery complete.", Status.CLOSED);
        Incident incident10 = new Incident(10, IncidentType.MISSING_PERSON, "Active search ongoing.", Status.ACTIVE);
        Incident incident11 = new Incident(11, IncidentType.REPORT_ABUSE, "Formal report recorded.", Status.ACTIVE);
        Incident incident12 = new Incident(12, IncidentType.MURDER, "Forensic data verified.", Status.VERIFIED);
        Incident incident13 = new Incident(13, IncidentType.THEFT, "Lead tracking in progress.", Status.ACTIVE);
        Incident incident14 = new Incident(14, IncidentType.MISSING_PERSON, "Subject located.", Status.CLOSED);
        Incident incident15 = new Incident(15, IncidentType.REPORT_ABUSE, "Case resolution logged.", Status.CLOSED);
        Incident incident16 = new Incident(16, IncidentType.MURDER, "Investigation active.", Status.ACTIVE);
        Incident incident17 = new Incident(17, IncidentType.MISSING_PERSON, "Information verified.", Status.VERIFIED);
        Incident incident18 = new Incident(18, IncidentType.THEFT, "Incident logged.", Status.INITIATED);
        Incident incident19 = new Incident(19, IncidentType.REPORT_ABUSE, "Information request sent.", Status.INITIATED);
        Incident incident20 = new Incident(20, IncidentType.MURDER, "Identity verified.", Status.VERIFIED);
        List<Incident> incidentList = List.of(
                incident1,
                incident2,
                incident3,
                incident4,
                incident5,
                incident6,
                incident7,
                incident8,
                incident9,
                incident10,
                incident11,
                incident12,
                incident13,
                incident14,
                incident15,
                incident16,
                incident17,
                incident18,
                incident19,
                incident20
        );

        return incidentList;
    }

    public List<Incident> filterIncidentByType(List<Incident> list, IncidentType incidentType) {
            return list
                    .stream()
                    .filter(incident -> incident.getIncidentType().equals(incidentType))
                    .toList();
    }

        public List<IncidentType> getAllIncidentType(List<Incident> list) {
                return list
                        .stream()
                        .map(Incident::getIncidentType)
                        .distinct()
                        .sorted()
                        .toList();
        }
}
