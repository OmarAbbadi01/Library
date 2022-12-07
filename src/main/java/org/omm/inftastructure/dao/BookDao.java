package org.omm.inftastructure.dao;

import org.omm.domain.model.Book;
import org.omm.domain.repository.BookRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class BookDao implements BookRepository {

    private final Connection[] connections;

    public BookDao(Connection... connections) {
        this.connections = connections;
    }

    @Override
    public Book findById(Long id) throws Exception {
        String query = "SELECT * FROM book WHERE id = ?";
        for (Connection conn : connections) {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id.intValue());
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return new Book(result.getLong("id"), result.getLong("author_id"), result.getString("title"));
            }
        }
        return null;
    }

    @Override
    public List<Book> findAll() {
        return null;
    }

    @Override
    public void create(Book book) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void update(Book book) {

    }
}
