package kz.javaee.demo.Collections;

import kz.javaee.demo.Model.Forum;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Forums {

    private Forum_messages forum_messages;


    public Forums(Forum_messages forum_messages) {
        this.forum_messages = forum_messages;
    }

    public Forum getForum(int id, Connection conn) {

        Forum forum = null;

        String sql = "SELECT * FROM forum WHERE id=?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String room = resultSet.getString(2);
                forum.setId(id);
                forum.setRoom(room);
                forum.setMessages(forum_messages.getMessagesInForum(id,conn));
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

        return forum;
    }




    public int insert(Forum forum, Connection conn) {

        String sql = "INSERT INTO forum (room) Values (?)";

        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, forum.getRoom());
            forum_messages.insert(forum, conn);
            return preparedStatement.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return 0;

    }


}
