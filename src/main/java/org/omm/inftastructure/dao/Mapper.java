package org.omm.inftastructure.dao;

import org.omm.domain.model.BookDto;
import org.omm.inftastructure.entity.Book;

public class Mapper {

    public static BookDto toBookDto(Book book) {
        return new BookDto(book.getId(), book.getAuthorId(), book.getTitle());
    }
}
