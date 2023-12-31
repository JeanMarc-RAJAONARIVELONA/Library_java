package Interface;

import Model.Book_Model;
import Connection.Db_connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookCrudOperations implements CrudOperations<Book_Model> {
    private final Connection connection = Db_connection.getConnection();
    private static final String table = "books";
    private static final String id_books = "id_books";
    private static final String bookName = "bookName";
    private static final String pageNumber = "pageNumber";
    private static final String topic = "topic";
    private static final String is_borrowed = "is_borrowed";
    private static final String id_author = "id_author";

    @Override
    public List<Book_Model> findAll() {
        List<Book_Model> allBooks = new ArrayList<>();
        String sql = "select * from " + table;
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Book_Model book = mapResultSetToBookModel(resultSet);
                allBooks.add(book);
            }
        } catch (SQLException e) {
            System.out.println("Selection error: " + e.getMessage());
        }
        return allBooks;
    }

    @Override
    public List<Book_Model> saveAll(List<Book_Model> toSave) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO " + table + " (" + bookName + ", " + pageNumber + ", " + topic + ", " + is_borrowed + ", " + id_author + ") VALUES (?, ?, ?, ?, ?)")) {
            connection.setAutoCommit(false);
            for (Book_Model book : toSave) {
                setPreparedStatementParameters(preparedStatement, book);
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException rollbackException) {
                rollbackException.printStackTrace();
            }
            System.out.println("Insert multiple error: " + e.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return toSave;
    }

    @Override
    public Book_Model save(Book_Model toSave) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO " + table + " (" + bookName + ", " + pageNumber + ", " + topic + ", " + is_borrowed + ", " + id_author + ") VALUES (?, ?, ?, ?, ?)")) {
            setPreparedStatementParameters(preparedStatement, toSave);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Insert error: " + e.getMessage());
        }
        return toSave;
    }

    @Override
    public Book_Model delete(Book_Model toDelete) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM " + table + " WHERE " + id_books + " = ?")) {
            preparedStatement.setInt(1, toDelete.getId_books());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Delete error: " + e.getMessage());
        }
        return toDelete;
    }

    private Book_Model mapResultSetToBookModel(ResultSet resultSet) throws SQLException {
        return new Book_Model(
                resultSet.getInt(id_books),
                resultSet.getString(bookName),
                resultSet.getInt(pageNumber),
                resultSet.getString(topic),
                resultSet.getBoolean(is_borrowed),
                resultSet.getInt(id_author)
        );
    }

    private void setPreparedStatementParameters(PreparedStatement preparedStatement, Book_Model book) throws SQLException {
        preparedStatement.setString(1, book.getBookName());
        preparedStatement.setInt(2, book.getPageNumber());
        preparedStatement.setString(3, book.getTopic());
        preparedStatement.setBoolean(4, book.is_borrowed());
        preparedStatement.setInt(5, book.getId_author());
    }
}