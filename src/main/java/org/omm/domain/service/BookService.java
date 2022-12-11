package org.omm.domain.service;

import org.omm.inftastructure.entity.Book;

import java.util.List;

public interface BookService {

    Book findById(Long id);

    List<Book> findAll();

    void create(Book book);

    void delete(Long id);

    void update(Book book);
}
