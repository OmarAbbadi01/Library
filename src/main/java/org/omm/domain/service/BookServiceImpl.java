package org.omm.domain.service;

import org.omm.domain.exception.BusinessException;
import org.omm.domain.model.BookDto;
import org.omm.domain.model.Status;
import org.omm.domain.repository.BookRepository;

import java.util.ArrayList;
import java.util.List;

public class BookServiceImpl implements BookService, Subject {

    private final BookRepository repository;

    private final List<Observer> observers;

    public BookServiceImpl(BookRepository bookRepository) {
        observers = new ArrayList<>();
        repository = bookRepository;
    }

    @Override
    public BookDto findById(Long id) throws Exception {
        BookDto bookDto = repository.findById(id);
        if (bookDto == null) {
            throw new BusinessException("Book Not Found", Status.NOT_FOUND);
        }
        return bookDto;
    }

    @Override
    public List<BookDto> findAll() throws Exception {
        return repository.findAll();
    }

    @Override
    public void create(BookDto bookDto) throws Exception {
        if (repository.existsById(bookDto.getId())) {
            throw new BusinessException("Book Already Exists", Status.BAD_REQUEST);
        }
        repository.create(bookDto);
        notifyObservers();
    }

    @Override
    public void delete(Long id) throws Exception {
        if (!repository.existsById(id)) {
            throw new BusinessException("ID Not Found", Status.NOT_FOUND);
        }
        repository.delete(id);
        notifyObservers();
    }

    @Override
    public void update(BookDto bookDto) throws Exception {
        if (!repository.existsById(bookDto.getId())) {
            throw new BusinessException("Book Not Found", Status.NOT_FOUND);
        }
        repository.update(bookDto);
        notifyObservers();
    }

    @Override
    public void register(Observer o) {
        observers.add(o);
    }

    @Override
    public void unregister(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() throws Exception {
        for (Observer o : observers) {
            o.updateData();
        }
    }

}
