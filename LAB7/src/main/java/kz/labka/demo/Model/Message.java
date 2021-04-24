package kz.javaee.demo.Model;

public class Message {

    private int id;
    private String message;
    private String author;
    private int likes;

    public Message(int id, String message, String author, int likes) {
        this.id = id;
        this.message = message;
        this.author = author;
        this.likes = likes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}
