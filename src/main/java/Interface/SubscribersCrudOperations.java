package Interface;

import Model.Subscribers_Model;
import Connection.Db_connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubscribersCrudOperations implements CrudOperations<Subscribers_Model> {
    private final Connection connection = Db_connection.getConnection();
    private static final String table = "subscribers";
    private static final String id_user = "id_user";
    private static final String user_name = "user_name";
    private static final String user_email = "user_email";
    private static final String references = "references";

    @Override
    public List<Subscribers_Model> findAll() {
        List<Subscribers_Model> allSubscribers = new ArrayList<>();
        String sql = "SELECT * FROM " + table;
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Subscribers_Model subscriber = mapResultSetToSubscribersModel(resultSet);
                allSubscribers.add(subscriber);
            }
        } catch (SQLException e) {
            System.out.println("Selection error: " + e.getMessage());
        }
        return allSubscribers;
    }

    @Override
    public List<Subscribers_Model> saveAll(List<Subscribers_Model> toSave) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO " + table + " VALUES (?, ?, ?, ?)")) {
            for (Subscribers_Model subscriber : toSave) {
                setPreparedStatementParameters(preparedStatement, subscriber);
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            System.out.println("Insert multiple error " + e.getMessage());
        }
        return toSave;
    }

    @Override
    public Subscribers_Model save(Subscribers_Model toSave) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO " + table + " (" + user_name + ", " + user_email + ", " + references + ") VALUES (?, ?, ?)")) {
            preparedStatement.setString(1, toSave.getUser_name());
            preparedStatement.setString(2, toSave.getUser_email());
            preparedStatement.setString(3, toSave.getReferences());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Insert error: " + e.getMessage());
        }
        return toSave;
    }

    @Override
    public Subscribers_Model delete(Subscribers_Model toDelete) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM " + table + " WHERE " + id_user + " = ?")) {
            preparedStatement.setInt(1, toDelete.getId_User());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Delete error: " + e.getMessage());
        }
        return toDelete;
    }

    private Subscribers_Model mapResultSetToSubscribersModel(ResultSet resultSet) throws SQLException {
        return new Subscribers_Model(
                resultSet.getInt(id_user),
                resultSet.getString(user_name),
                resultSet.getString(user_email),
                resultSet.getString(references)
        );
    }

    private void setPreparedStatementParameters(PreparedStatement preparedStatement, Subscribers_Model subscriber) throws SQLException {
        preparedStatement.setString(1, subscriber.getUser_name());
        preparedStatement.setString(2, subscriber.getUser_email());
        preparedStatement.setString(3, subscriber.getReferences());
    }
}
