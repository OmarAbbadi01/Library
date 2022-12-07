package org.omm.domain.repository;


import org.omm.domain.model.Book;

import java.util.List;

public interface BookRepository {

    Book findById(Long id) throws Exception;

    List<Book> findAll();

    void create(Book book);

    void delete(Long id);

    void update(Book book);
}
