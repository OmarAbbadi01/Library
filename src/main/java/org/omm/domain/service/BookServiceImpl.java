package org.omm.domain.service;

import lombok.AllArgsConstructor;
import org.omm.inftastructure.entity.Book;
import org.omm.domain.repository.BookRepository;

import java.util.List;

@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository repository;

    // all methods should be implemented correctly
    @Override
    public Book findById(Long id) {
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
