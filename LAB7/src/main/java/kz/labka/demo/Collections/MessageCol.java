package kz.javaee.demo.Collections;

import kz.javaee.demo.Model.Message;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MessageCol {


    public MessageCol() {
    }

    public kz.javaee.demo.Model.Message getMessage(int id, Connection conn) {

        kz.javaee.demo.Model.Message message = null;

        String sql = "SELECT * FROM message WHERE id=?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getInt(1);
                String text = resultSet.getString(2);
                String author = resultSet.getString(3);
                int likes = resultSet.getInt(4);
                message = new kz.javaee.demo.Model.Message(id, text, author, likes);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return message;

    }


    public int insert(kz.javaee.demo.Model.Message message, Connection conn) {

        String sql = "INSERT INTO message (message, author, likes) Values (?, ?, ?)";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, message.getMessage());
            preparedStatement.setString(2, message.getAuthor());
            preparedStatement.setInt(3, message.getLikes());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return 0;

    }

    public int update(kz.javaee.demo.Model.Message message, Connection conn) {

        String sql = "UPDATE message SET message = ?, author = ?, likes = ?   WHERE id = ?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, message.getMessage());
            preparedStatement.setString(2, message.getAuthor());
            preparedStatement.setInt(3, message.getLikes());
            preparedStatement.setInt(4, message.getId());
            return preparedStatement.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return 0;
    }

}
