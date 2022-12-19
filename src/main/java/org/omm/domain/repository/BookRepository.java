package org.omm.domain.repository;


import org.omm.domain.model.BookDto;

import java.util.List;

public interface BookRepository {

    BookDto findById(Long id) throws Exception;

    List<BookDto> findAll() throws Exception;

    void create(BookDto book) throws Exception;

    void delete(Long id) throws Exception;

    void update(BookDto book) throws Exception;

    boolean existsById(Long id) throws Exception;
}
