import models.User;
import services.AuthService;

import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static AuthService authService = new AuthService();
    private static User currentUser = null;

    public static void main(String[] args) {
        /*
            This app will allow a user to register and log in,
            then the user can create, view, update, and delete personal notes.

                Features:
                - User Authentication
                    - registration
                    - login

                - Notes Management
                    - create a note
                    - view a note
                    - delete a note

                     - update a note

                - Password security
                    - store the user passwords securely
        */
        int choice = 1;

        while(choice!=0){
            System.out.println("\n===Notes and Password Manager===");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("0. Exit");
            System.out.print("Enter your choice:");

            // Getting input from user
            choice = readInt();

            switch (choice){
                case 1:
                    // register
                    register();
                    break;
                case 2:
                    // login
                    login();
                    // is user logged in
                    if(currentUser!=null){
                        // display a menu or something
                        System.out.print("Dashboard...");
                    }
                    break;
                case 0:
                    // exit
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }

        }

        scanner.close();
    }

    private static int readInt(){
        // if invalid input value then repeat until its valid
        while(!scanner.hasNextInt()){
            System.out.print("Please enter a valid number: ");
            scanner.next();// try again
        }
        return  scanner.nextInt();
    }

    private static void register(){
        System.out.print("Enter your username:");
        String username = scanner.next();
        System.out.print("Enter a password:");
        String password = scanner.next();
        authService.register(username,password);
    }

    private static void login(){
        System.out.print("Enter your username:");
        String username = scanner.next();
        System.out.print("Enter a password:");
        String password = scanner.next();
        currentUser = authService.login(username, password);
    }


}