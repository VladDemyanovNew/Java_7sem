package services;

import java.util.LinkedList;
import java.sql.*;
import java.io.IOException;

import beans.*;
import factories.*;

public class CommentService {

    public LinkedList<Comment> getAllByReferenceId(int linkedReferenceId) {
        LinkedList<Comment> comments = new LinkedList<Comment>();
        String query = "SELECT * FROM Comments WHERE ReferenceId = ?";

        try (Connection connection = SqlConnectionFactory.createConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, linkedReferenceId);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("Id");
                int referenceId = resultSet.getInt("ReferenceId");
                String sessionId = resultSet.getString("SessionId");
                Date stamp = resultSet.getDate("Stamp");
                String text = resultSet.getString("Text");

                Comment comment = new Comment(id, referenceId, sessionId, stamp, text);
                comments.add(comment);
            }

            return comments;
        }
        catch (SQLException error) {
            error.printStackTrace();
            return comments;
        }
    }

    public Comment create(Comment comment) {
        String query = "INSERT INTO Comments (ReferenceId, SessionId, Stamp, Text) VALUES (?, ?, ?, ?)";

        try (Connection connection = SqlConnectionFactory.createConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            statement.setInt(1, comment.getReferenceId());
            statement.setString(2, comment.getSessionId());
            statement.setDate(3, comment.getStamp());
            statement.setString(4, comment.getText());

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating comment failed, no rows affected.");
            }

            ResultSet generatedKeys = statement.getGeneratedKeys();
            generatedKeys.next();

            comment.setId(generatedKeys.getInt(1));

            generatedKeys.close();

            return comment;
        }
        catch (SQLException error) {
            error.printStackTrace();
            return null;
        }
    }

    public void update(int id, Comment commentUpdateData) {
        String query = "UPDATE Comments SET Text = ? WHERE Id = ?";

        try (Connection connection = SqlConnectionFactory.createConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, commentUpdateData.getText());
            statement.setInt(2, id);

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Updating comment failed, no rows affected.");
            }
        }
        catch (SQLException error) {
            error.printStackTrace();
        }
    }

    public void delete(int id) {
        String query = "DELETE Comments WHERE Id = ?";

        try (Connection connection = SqlConnectionFactory.createConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            statement.setInt(1, id);

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Deleting comment failed, no rows affected.");
            }
        }
        catch (SQLException error) {
            error.printStackTrace();
        }
    }
}