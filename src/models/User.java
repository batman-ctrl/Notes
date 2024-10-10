package models;

import java.util.ArrayList;

public class User {
    private String username;
    private String passwordHash;
    private ArrayList<Note> notes;

    public User(String username, String passwordHash, ArrayList<Note> notes) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.notes = notes;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
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
