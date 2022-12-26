package services;

import java.util.LinkedList;
import java.sql.*;
import java.io.IOException;

import beans.*;
import factories.*;

public class ReferenceService {

    public LinkedList<Reference> getAll() {
        LinkedList<Reference> references = new LinkedList<Reference>();
        String query = "SELECT * FROM [References]";

        try (Connection connection = SqlConnectionFactory.createConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("Id");
                String url = resultSet.getString("Url");
                String description = resultSet.getString("Description");
                int minus = resultSet.getInt("Minus");
                int plus = resultSet.getInt("Plus");

                Reference reference = new Reference(id, url, description, minus, plus);
                references.add(reference);
            }

            return references;
        }
        catch (SQLException error) {
            error.printStackTrace();
            return references;
        }
    }

    public Reference create(Reference reference) {
        String query = "INSERT INTO [References] ([Url], [Description], Minus, Plus) VALUES (?, ?, ?, ?)";

        try (Connection connection = SqlConnectionFactory.createConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, reference.getUrl());
            statement.setString(2, reference.getDescription());
            statement.setInt(3, 0);
            statement.setInt(4, 0);

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating reference failed, no rows affected.");
            }

            ResultSet generatedKeys = statement.getGeneratedKeys();
            generatedKeys.next();

            reference.setId(generatedKeys.getInt(1));

            generatedKeys.close();

            return reference;
        }
        catch (SQLException error) {
            error.printStackTrace();
            return null;
        }
    }

    public void update(int id, Reference referenceUpdateData) {
        String query = "UPDATE [References] SET [Url] = ?, [Description] = ? WHERE Id = ?";

        try (Connection connection = SqlConnectionFactory.createConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, referenceUpdateData.getUrl());
            statement.setString(2, referenceUpdateData.getDescription());
            statement.setInt(3, id);

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Updating reference failed, no rows affected.");
            }
        }
        catch (SQLException error) {
            error.printStackTrace();
        }
    }

    public void delete(int id) {
        String query = "DELETE [References] WHERE Id = ?";

        try (Connection connection = SqlConnectionFactory.createConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            statement.setInt(1, id);

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Deleting reference failed, no rows affected.");
            }
        }
        catch (SQLException error) {
            error.printStackTrace();
        }
    }
}