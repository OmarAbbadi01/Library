package org.omm.domain.service;

import lombok.AllArgsConstructor;
import org.omm.domain.model.BookDto;
import org.omm.domain.repository.BookRepository;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class BookServiceImpl implements BookService,  Subject  {

    private final BookRepository repository;

    ArrayList<Observer> observers;

    public BookServiceImpl(BookRepository bookRepository){
        observers = new ArrayList<>();
        repository = bookRepository;
    }



    @Override
    public BookDto findById(Long id) throws Exception {
        BookDto bookDto = repository.findById(id);
        if (bookDto == null) {
            throw new Exception("Book Not Found");
        }
        return bookDto;
    }

    @Override
    public List<BookDto> findAll() throws Exception {
        return repository.findAll();
    }

    @Override
    public void create(BookDto book) throws Exception {
        repository.create(book);
        notifyObservers();
    }

    @Override
    public void delete(Long id) throws Exception {
        if (!repository.existsById(id)) {
            throw new Exception("ID Not Found");
        }
        repository.delete(id);
        notifyObservers();
    }

    @Override
    public void update(BookDto book) throws Exception {
        if (!repository.existsById(book.getId())) {
            throw new Exception("Book Not Found");
        }
        repository.update(book);
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
        for(Observer o : observers){
            o.updateData();
        }
    }

}
