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

    private final Connection[] connections;

    public BookDao(Connection... connections) {
        this.connections = connections;
    }

    @Override
    public BookDto findById(Long id) throws Exception {
        String query = "SELECT * FROM book WHERE id = ?";
        for (Connection conn : connections) {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id.intValue());
            ResultSet result = statement.executeQuery(); // id, author_id, title
            if (result.next()) {
                Long id_ = result.getLong("id");
                Long author_id = result.getLong("author_id");
                String title = result.getString("title");
                Book book = new Book(id_, author_id, title);
                return Mapper.toBookDto(book);
            }
        }
        return null;
    }

    @Override
    public List<BookDto> findAll() throws Exception {
        String query = "SELECT * FROM book";
        List<BookDto> books = new ArrayList<>();
        for (Connection conn : connections) {
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet result = statement.executeQuery(); // id, author_id, title
            while (result.next()) {
                Long id = result.getLong("id");
                Long author_id = result.getLong("author_id");
                String title = result.getString("title");
                Book book = new Book(id, author_id, title);
                books.add(Mapper.toBookDto(book));
            }
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

    }

    @Override
    public void update(BookDto book) throws Exception {

    }

    @Override
    public boolean existsById(Long id) throws Exception {
        String query = "SELECT COUNT(*) FROM book WHERE id = ?";
        for (Connection conn : connections) {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id.intValue());
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                if (result.getString("count").compareTo(String.valueOf(Long.valueOf(0L))) > 0) {
                    return true;
                }
            }
        }
        return false;
    }
}
