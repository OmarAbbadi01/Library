package org.omm.inftastructure.dao;

import org.omm.domain.model.BookDto;
import org.omm.domain.repository.BookRepository;
import org.omm.inftastructure.entity.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BookDao implements BookRepository {

    private final List<Connection> connections;

    public BookDao(List<Connection> connections) {
        this.connections = connections;
    }

    private void before() throws Exception{
        String query = "CREATE TABLE IF NOT EXISTS book  \n" +
                "(\n" +
                "\tid BIGINT, \n" +
                "\tauthor_id BIGINT,\n" +
                "\ttitle VARCHAR,\n" +
                "\tprimary key (id)\n" +
                ");";
        for (Connection c : connections) {
            PreparedStatement p = c.prepareStatement(query);
            p.executeUpdate();
        }
    }
    @Override
    public BookDto findById(Long id) throws Exception {
        String query = "SELECT * FROM book WHERE id = ?";
        Connection conn = connections.get(0);
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, id.intValue());
        ResultSet result = statement.executeQuery(); // id, author_id, title
        if (result.next()) {
            Long authorId = result.getLong("author_id");
            String title = result.getString("title");
            Book book = new Book(id, authorId, title);
            return Mapper.toBookDto(book);
        }
        return null;
    }


    @Override
    public List<BookDto> findAll() throws Exception {
        before();
        String query = "SELECT * FROM book";
        List<BookDto> books = new ArrayList<>();
        Connection conn = connections.get(0);
        PreparedStatement statement = conn.prepareStatement(query);
        ResultSet result = statement.executeQuery(); // id, author_id, title
        while (result.next()) {
            Long id = result.getLong("id");
            Long authorId = result.getLong("author_id");
            String title = result.getString("title");
            Book book = new Book(id, authorId, title);
            books.add(Mapper.toBookDto(book));
        }
        return books;
    }

    @Override
    public void create(BookDto book) throws Exception {
        String query = "INSERT INTO book VALUES (?, ?, ?)";
        for (Connection conn : connections) {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, book.getId().intValue());
            statement.setInt(2, book.getAuthorId().intValue());
            statement.setString(3, book.getTitle());
            statement.executeUpdate();
        }
    }

    @Override
    public void delete(Long id) throws Exception {
        String query = "DELETE FROM book WHERE id = ?";
        for (Connection conn : connections) {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id.intValue());
            statement.executeUpdate();
        }
    }

    @Override
    public void update(BookDto book) throws Exception {
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
        String query = "SELECT COUNT(*) FROM book WHERE id = ?";
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
