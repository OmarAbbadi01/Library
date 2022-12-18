package org.omm.application;

import lombok.AllArgsConstructor;
import org.omm.application.response.BookResponse;
import org.omm.application.response.Status;
import org.omm.domain.model.BookDto;
import org.omm.domain.service.BookService;

import java.util.List;

@AllArgsConstructor
public class BookController {

    private final BookService service;

    private final BookValidator validator;

    // Endpoints
    public BookResponse<BookDto> findById(Long id) throws Exception {
        BookDto bookDto = service.findById(id);
        return new BookResponse<>(bookDto, Status.OK);
    }

    public BookResponse<List<BookDto>> findAll() throws Exception {
        List<BookDto> books = service.findAll();
        return new BookResponse<>(books, Status.OK);
    }

    public BookResponse<BookDto> create(BookDto bookDto) throws Exception {
        validator.validateForCreateUpdate(bookDto);
        service.create(bookDto);
        return new BookResponse<>(bookDto, Status.CREATED);
    }

    public BookResponse<String> delete(Long id) throws Exception {
        service.delete(id);
        return new BookResponse<>("ID: " + id, Status.DELETED);
    }

    public BookResponse<BookDto> update(BookDto bookDto) throws Exception {
        validator.validateForCreateUpdate(bookDto);
        service.update(bookDto);
        return new BookResponse<>(bookDto, Status.UPDATED);
    }

}
