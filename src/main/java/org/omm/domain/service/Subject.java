package org.omm.domain.service;

public interface Subject {

    void register(Observer o);

    void unregister(Observer o);

    void notifyObservers() throws Exception;
}
