package kz.javaee.demo.Controller;

import kz.javaee.demo.Collections.*;
import kz.javaee.demo.Model.User;

public class ForumController {

    private UsersCollection usersCollection ;
    private Messages messages;
    private Token token;
    private Forums forums;
    private Forum_messages forum_messages;

    public ForumController() {
        usersCollection = new UsersCollection();
        messages = new Messages();
        token = new Token();
        forum_messages = new Forum_messages(messages);
        forums = new Forums(forum_messages);
    }

    public UsersCollection getUsersCollection() {
        return usersCollection;
    }

    public void setUsersCollection(UsersCollection usersCollection) {
        this.usersCollection = usersCollection;
    }

    public Messages getMessages() {
        return messages;
    }

    public void setMessages(Messages messages) {
        this.messages = messages;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public Forums getForums() {
        return forums;
    }

    public void setForums(Forums forums) {
        this.forums = forums;
    }

    public Forum_messages getForum_messages() {
        return forum_messages;
    }

    public void setForum_messages(Forum_messages forum_messages) {
        this.forum_messages = forum_messages;
    }

    public User auth (int token) {
        String username = this.token.getToken(token);
        return usersCollection.getUser(username);
    }

    public User signIn (String name, String passwd ) {

        User user = usersCollection.getUser(name);
        if (user != null) {
            if (!user.getPassword().equals(passwd)) {
                return null;
            }

        }
        return user;
    }

    public String createToken (String name ) {

        Integer token = this.token.getTokens();
        this.token.insert(token, name);
        String toStringToken = Integer.toString(token);
        return toStringToken;

    }


}
