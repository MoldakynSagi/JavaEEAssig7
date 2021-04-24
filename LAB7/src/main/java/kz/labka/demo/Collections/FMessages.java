package kz.javaee.demo.Collections;

import kz.javaee.demo.Model.Forum;
import kz.javaee.demo.Model.Message;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Forum_messages {

    private Messages messagesCollection;

    public Forum_messages(Messages messages) {
        this.messagesCollection = messages;
    }

    public ArrayList<Message> getMessagesInForum (int ForumID, Connection conn) {

        ArrayList<Message> messages = new ArrayList<>();
        String sql = "SELECT * FROM forum_messages where  forum_id = ? ";

        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, ForumID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(2);
                Message message = messagesCollection.getMessage(id, conn);
                messages.add(message);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return messages;
    }

    public int insert(Forum forum, Connection conn) {

        ArrayList<Integer> messIDs = new ArrayList<>();
        for (Message message : forum.getMessages()) {
            Integer messID = messagesCollection.insert(message, conn);
            messIDs.add(messID);
        }

        for (int messId: messIDs ) {

            String sql = "INSERT INTO forum_messages (forum_id, message_id) Values (?, ?)";

            try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                preparedStatement.setInt(1, forum.getId());
                preparedStatement.setInt(2, messId);
                preparedStatement.executeUpdate();
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }

        return 0;
    }


    public int insertMessage(int forumID, Message message, Connection conn) {
        int messageId = messagesCollection.insert(message, conn);

        String sql = "INSERT INTO forum_messages (forum_id, message_id) Values (?, ?)";

        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, forumID);
            preparedStatement.setInt(2, messageId);
            preparedStatement.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex);
        }

        return 0;
    }



}
