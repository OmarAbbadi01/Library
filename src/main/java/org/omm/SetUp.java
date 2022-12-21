package org.omm;

import org.omm.application.BookController;
import org.omm.application.BookValidator;
import org.omm.application.BookValidatorImpl;
import org.omm.domain.repository.BookRepository;
import org.omm.domain.service.BookService;
import org.omm.domain.service.BookServiceImpl;
import org.omm.domain.service.Subject;
import org.omm.inftastructure.connection.*;
import org.omm.inftastructure.repositoryImpl.BookRepositoryImpl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class SetUp {

    private List<Connection> connections;
    private BookService service;
    private BookRepository repository;
    private BookValidator validator;
    private BookController controller;
    private Dashboard dashboard;

    private List<Connection> setUpConnections() {
        ConnectionFactory postgreSqlFactory = new PostgreSqlFactory("library", "root", "library");
        ConnectionFactory mySqlFactory = new MySqlFactory("library", "root12341234", "public");
        if (connections == null) {
            connections = new ArrayList<>();
            connections.add(postgreSqlFactory.getConnection());
            connections.add(mySqlFactory.getConnection());
        }
        return connections;
    }

    private BookRepository setUpRepository() {
        if (repository == null)
            repository = new BookRepositoryImpl(setUpConnections());
        return repository;
    }

    private BookService setUpService() {
        if (service == null)
            service = new BookServiceImpl(setUpRepository());
        return service;
    }

    private BookValidator setUpValidator() {
        if (validator == null)
            validator = new BookValidatorImpl();
        return validator;
    }

    public BookController setUpController() {
        if (controller == null)
            controller = new BookController(setUpService(), setUpValidator());
        return controller;
    }

    public Subject setUpSubject() {
        return (Subject) setUpService();
    }

    public Dashboard setUpDashboard() throws Exception {
        if (dashboard == null)
            dashboard = new Dashboard(setUpSubject(), setUpService());
        return dashboard;
    }
}
