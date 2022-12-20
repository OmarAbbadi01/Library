package org.omm.domain.service;

import org.omm.domain.model.BookDto;

import java.util.List;

public interface Subject {

     void register(Observer o);
     void unregister(Observer o);
     void notifyObservers() throws Exception;
}
