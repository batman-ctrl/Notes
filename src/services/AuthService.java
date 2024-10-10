package services;

import models.User;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class AuthService {

    // We would use a database here...
    private ArrayList<User> users;

    public AuthService(){
        users = new ArrayList<>();
    }

    public boolean register(String username, String password){
        // If user exists
        if(userExists(username)!=null){
            System.out.println("Username already exists.");
            return false;
        }
        // encrypted password
        String passwordHash = hashPassword(password);
        users.add(new User(username, passwordHash));
        System.out.println("User registerd successfully");
        return  true;

    }

    // Login user
    public User login(String username, String password){
        User user = userExists(username);
        if(user!=null){
            String passwordHash = hashPassword(password);
            if(user.getPasswordHash().equals(passwordHash)){
                System.out.println("Login successful");
                return user;
            }
        }
        System.out.println("Invalid username or password");
        return null;
    }

    // Utility methods (helper methods)
    private User userExists(String username){
        for(User user : users){
            if(user.getUsername().equals(username)){
                return user;
            }
        }
        return null;
    }

    // Password hashing using SHA-256
    private String hashPassword(String password){
        /*
            Password hashing is the process of converting
            a plaintext password into a hashed value using a hash function.

            A hash function takes an input and produces a fixed size of string of bytes (hash).

            Commonly used algorithms for password hashing are:
             1 - MD5 (produces hash of 128 bits)
             2 - SHA-1 (produces hash of 160 bits)
             3 - SHA-256 (SHA-3) (produces hashes of 256 bits)

         */

        try {

            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte hash [] = md.digest(password.getBytes());

            // Convert byte array into a string representation
            StringBuilder hashString = new StringBuilder();
            for(byte b:hash){
                hashString.append(b);
            }

            return hashString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

    }



}
