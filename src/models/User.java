package models;

import java.util.ArrayList;

public class User {
    private String username;
    private String passwordHash;
    private ArrayList<Note> notes;

    public User(String username, String passwordHash) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.notes = new ArrayList<>();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getUsername() {
        return username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public ArrayList<Note> getNotes() {
        return notes;
    }

    public void setNotes(ArrayList<Note> notes) {
        this.notes = notes;
    }

    public void addNote(Note note){
        notes.add(note);
    }

    public void removeNote(int noteIndex){
        // 0 - length of all notes
        if(noteIndex>=0 && noteIndex<notes.size()){
            notes.remove(noteIndex);
        }else{
            System.out.println("Note does not exist.");
        }
    }
}
