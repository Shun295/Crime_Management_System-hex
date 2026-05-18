package com.controller;

import com.enums.IncidentType;
import com.enums.Role;
import com.enums.Status;
import com.exception.UserNotFoundException;
import com.model.Incident;
import com.model.User;
import com.service.IncidentService;
import com.service.UserService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class MainController {
    public static void main(String[] args) {

        UserService userService = new UserService();
        IncidentService incidentService = new IncidentService();

        Scanner sc = new Scanner(System.in);

        System.out.println("-------CMS LOGIN------");
        System.out.println("Enter Username:");
        String username = sc.next();

        System.out.println("Enter Password:");
        String password = sc.next();

        try {
            User user = userService.authenticateUser(username, password);

            System.out.println("Welcome " + user.getUsername());
            System.out.println("Role is " + user.getRole());

            if (user.getRole().equals(Role.OFFICER)) {

                while (true) {
                    System.out.println("1. View Incidents");
                    System.out.println("2. Filter Incidents by Status");
                    System.out.println("3. Insert Incident");
                    System.out.println("0. Exit");

                    int op = sc.nextInt();

                    if (op == 0) {
                        break;
                    }

                    switch (op) {

                        case 1:
                            List<Incident> incidents =
                                    incidentService.viewIncident(user.getId());

                            incidents.forEach(System.out::println);
                            break;

                        case 2:
                            System.out.println("Enter the status:");
                            String statusVal = sc.next();

                            Status status =
                                    Status.valueOf(statusVal.toUpperCase());

                            List<Incident> filteredIncidents =
                                    incidentService.filterIncidentByStatus(
                                            user.getId(),
                                            status
                                    );

                            filteredIncidents.forEach(System.out::println);
                            break;

                        case 3:
                            System.out.println("Enter Incident Type:");
                            String typeVal = sc.next();

                            sc.nextLine(); // clear buffer

                            System.out.println("Enter Progress Details:");
                            String progress = sc.nextLine();

                            System.out.println("Enter Incident Status:");
                            String statusInput = sc.next();

                            IncidentType incidentType =
                                    IncidentType.valueOf(
                                            typeVal.toUpperCase()
                                                    .replace(" ", "_")
                                    );

                            Status incidentStatus =
                                    Status.valueOf(
                                            statusInput.toUpperCase()
                                    );

                            Incident incident = new Incident(
                                    0,
                                    incidentType,
                                    progress,
                                    incidentStatus
                            );

                            incidentService.insertIncident(
                                    user.getId(),
                                    incident
                            );

                            System.out.println("Incident inserted successfully");
                            break;

                        default:
                            System.out.println("Invalid option");
                    }
                }
            }

        } catch (SQLException | UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}