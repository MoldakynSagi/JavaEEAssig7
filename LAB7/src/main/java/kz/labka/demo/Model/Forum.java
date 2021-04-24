package kz.javaee.demo.Model;

import java.util.ArrayList;

public class Forum {

    private int id;
    private ArrayList<Message> messages;
    private String room;

    public Forum(int id, ArrayList<Message> messages, String room) {
        this.id = id;
        this.messages = messages;
        this.room = room;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }

}
