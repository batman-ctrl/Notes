import models.Note;
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
                        dashboard();
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

    private static void dashboard(){
        int choice = 0;
        while(choice!=5){
            System.out.println("\n===Dashboard===");
            System.out.println("1. Create note");
            System.out.println("2. View note");
            System.out.println("3. Update note");
            System.out.println("4. Delete note");
            System.out.println("5. Logout");
            System.out.print("Enter your choice: ");
            choice = readInt();

            switch (choice){
                case 1:
                    // create a note
                    createNote();
                    break;
                case 2:
                    // view my notes
                    viewNotes();
                    break;
                case 3:
                    updateNote();
                    break;
                case 4:
                    deleteNote();
                    break;
                case 5:
                    //Logout
                    currentUser = null;
                    System.out.println("Logged out sucessfully");
                    break;
            }

            // escape while loop as well
            if(currentUser==null){
                break;
            }
        }
    }

    private static void createNote(){
        scanner.nextLine();
        System.out.print("Enter note title: ");
        String title = scanner.nextLine();
        System.out.println("Enter note content: ");
        String content = scanner.nextLine();

        Note note = new Note(title, content);
        currentUser.addNote(note);
        System.out.println("Note added successfully!");
    }

    private static  void viewNotes(){
        System.out.println("\n===Your Notes===");
        int index = 0;
        for(Note note : currentUser.getNotes()){
            System.out.println("Note ID "+index);
            note.displayNote();
            System.out.println("-----------------------");
            index++;
        }
        if(index==0){
            System.out.println("You have no notes.");
        }
    }

    private static void deleteNote(){
        viewNotes();
        System.out.print("Enter the Note ID to delte:");
        int id = readInt();

        if(id>=0 && id<currentUser.getNotes().size()){
            currentUser.removeNote(id);
            System.out.println("Note deleted successfully!");
        }else{
            System.out.println("Invalid note id");
        }
    }

    private static void updateNote(){
        viewNotes();
        System.out.print("Enter Note ID to update: ");
        int id = readInt();

        // if id is valid
        if(id>=0 && id<currentUser.getNotes().size()){

            Note note = currentUser.getNotes().get(id);

            scanner.nextLine();
            System.out.print("Enter a new title (leave blank to keep current):");
            String newtitle = scanner.nextLine();
            if(!newtitle.isEmpty()){
                note.setTitle(newtitle);
            }
            // if no new title
            System.out.print("Enter new content (leave blank to keep current):");
            String newContent = scanner.nextLine();
            if(!newContent.isEmpty()){
                note.setContent(newContent);
            }
            System.out.println("Note updated successfully!");

        }else{
            System.out.println("Invalid note Id.");
        }

    }
}