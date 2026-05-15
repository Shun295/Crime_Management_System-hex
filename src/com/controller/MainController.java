package com.controller;

import com.exception.UserNotFoundException;
import com.model.User;
import com.service.UserService;

import java.sql.SQLException;
import java.util.Scanner;

public class MainController {
    public static void main(String[] args) {
        UserService userService = new UserService();
        Scanner sc = new Scanner(System.in);
        System.out.println("-------CMS LOGIN------");
        System.out.println("Enter Username:");
        String username=sc.next();
        System.out.println("Enter Password:");
        String password=sc.next();
        try {
            User user = userService.authenticateUser(username, password);
            System.out.println("Welcome " + user.getUsername() );
            System.out.println("Role is " + user.getRole());
        }
        catch(SQLException | UserNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
