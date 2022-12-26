package org.omm.inftastructure.repositoryImpl;

import org.omm.domain.model.BookDto;
import org.omm.domain.repository.BookRepository;
import org.omm.inftastructure.dao.GenericDao;
import org.omm.inftastructure.entity.Book;

import java.util.ArrayList;
import java.util.List;

public class BookRepositoryImpl implements BookRepository {

    private final GenericDao<Book> bookDao;

    public BookRepositoryImpl(GenericDao<Book> bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public BookDto findById(Long id) throws Exception {
        return Mapper.toBookDto(bookDao.findById(id));
    }

    @Override
    public List<BookDto> findAll() throws Exception {
        List<Book> books = bookDao.findAll();
        List<BookDto> bookDtos = new ArrayList<>();
        for (Book book : books) {
            bookDtos.add(Mapper.toBookDto(book));
        }
        return bookDtos;
    }

    @Override
    public void create(BookDto bookDto) throws Exception {
        bookDao.create(Mapper.toBook(bookDto));
    }

    @Override
    public void delete(Long id) throws Exception {
        bookDao.delete(id);
    }

    @Override
    public void update(BookDto bookDto) throws Exception {
        bookDao.update(Mapper.toBook(bookDto));
    }

    @Override
    public boolean existsById(Long id) throws Exception {
        return bookDao.existsById(id);
    }
}
