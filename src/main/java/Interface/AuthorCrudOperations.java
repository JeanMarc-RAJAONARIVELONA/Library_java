package Interface;

import Model.Author_Model;
import Connection.Db_connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorCrudOperations implements CrudOperations<Author_Model> {
    private final Connection connection = Db_connection.getConnection();
    private static final String table = "author";
    private static final String id_author = "id_author";
    private static final String author_name = "author_name";
    private static final String author_sex = "author_sex";

    @Override
    public List<Author_Model> findAll() {
        List<Author_Model> allAuthors = new ArrayList<>();
        String sql = "SELECT * FROM " + table;
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Author_Model author = mapResultSetToAuthorModel(resultSet);
                allAuthors.add(author);
            }
        } catch (SQLException e) {
            System.out.println("Selection error: " + e.getMessage());
        }
        return allAuthors;
    }

    @Override
    public List<Author_Model> saveAll(List<Author_Model> toSave) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO " + table + " VALUES (?, ?, ?, ?, ?, ?)")) {
            for (Author_Model author : toSave) {
                setPreparedStatementParameters(preparedStatement, author);
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            System.out.println("insert multiple error "+e.getMessage());
        }
        return toSave;
    }


    @Override
    public Author_Model save(Author_Model toSave) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO " + table + " (" + author_name + ", " + author_sex + ") VALUES (?, ?)")) {
            preparedStatement.setString(1, toSave.getAuthor_name());
            preparedStatement.setString(2, toSave.getAuthor_sex());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Insert error: " + e.getMessage());
        }
        return toSave;
    }

    @Override
    public Author_Model delete(Author_Model toDelete) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM " + table + " WHERE " + id_author + " = ?")) {
            preparedStatement.setInt(1, toDelete.getId_author());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Delete error: " + e.getMessage());
        }
        return toDelete;
    }

    private Author_Model mapResultSetToAuthorModel(ResultSet resultSet) throws SQLException {
        return new Author_Model(
                resultSet.getInt(id_author),
                resultSet.getString(author_name),
                resultSet.getString(author_sex)
        );
    }
    private void setPreparedStatementParameters(PreparedStatement preparedStatement, Author_Model author) throws SQLException {
        preparedStatement.setString(1,author.getAuthor_name());
        preparedStatement.setString(2,author.getAuthor_sex());
    }
}
