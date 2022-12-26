package org.omm.inftastructure.dao;

import org.omm.inftastructure.entity.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BookDaoImpl implements GenericDao<Book> {
    private final List<Connection> connections;

    public BookDaoImpl(List<Connection> connections) {
        this.connections = connections;
    }

    @Override
    public Book findById(Long id) throws Exception {
        String query = "SELECT * FROM book WHERE id = ? AND deleted = FALSE";
        Connection conn = connections.get(0);
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, id.intValue());
        ResultSet result = statement.executeQuery(); // id, author_id, title
        if (result.next()) {
            Long authorId = result.getLong("author_id");
            String title = result.getString("title");
            Book book = new Book(id, authorId, title);
            return book;
        }
        return null;
    }

    @Override
    public List<Book> findAll() throws Exception {
        String query = "SELECT * FROM book WHERE deleted = FALSE";
        List<Book> books = new ArrayList<>();
        Connection conn = connections.get(0);
        PreparedStatement statement = conn.prepareStatement(query);
        ResultSet result = statement.executeQuery(); // id, author_id, title
        while (result.next()) {
            Long id = result.getLong("id");
            Long authorId = result.getLong("author_id");
            String title = result.getString("title");
            Book book = new Book(id, authorId, title);
            books.add(book);
        }
        return books;
    }

    @Override
    public void create(Book book) throws Exception {
        String query = "INSERT INTO book VALUES (?, ?, ?, ?)";
        for (Connection conn : connections) {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, book.getId().intValue());
            statement.setInt(2, book.getAuthorId().intValue());
            statement.setString(3, book.getTitle());
            statement.setBoolean(4, false);
            statement.executeUpdate();
        }
    }

    @Override
    public void delete(Long id) throws Exception {
        String query = "UPDATE book SET deleted = TRUE WHERE id = ?";
        for (Connection conn : connections) {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id.intValue());
            statement.executeUpdate();
        }
    }

    @Override
    public void update(Book book) throws Exception {
        String query = "UPDATE book SET id = ?, author_id = ?, title = ? WHERE id = ?";
        for (Connection conn : connections) {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, book.getId().intValue());
            statement.setInt(2, book.getAuthorId().intValue());
            statement.setString(3, book.getTitle());
            statement.setInt(4, book.getId().intValue());
            statement.executeUpdate();
        }
    }

    @Override
    public boolean existsById(Long id) throws Exception {
        String query = "SELECT COUNT(id) AS COUNT FROM book WHERE id = ? AND deleted = FALSE";
        Connection conn = connections.get(0);
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, id.intValue());
        ResultSet result = statement.executeQuery();
        if (result.next()) {
            return result.getString("count").compareTo(String.valueOf(Long.valueOf(0L))) > 0;
        }
        return false;
    }
}

