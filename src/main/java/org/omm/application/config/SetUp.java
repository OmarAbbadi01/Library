package org.omm.application.config;

import org.omm.application.controller.BookController;
import org.omm.application.validator.BookValidator;
import org.omm.application.validator.BookValidatorImpl;
import org.omm.domain.repository.BookRepository;
import org.omm.domain.service.BookService;
import org.omm.domain.service.BookServiceImpl;
import org.omm.domain.service.Subject;
import org.omm.inftastructure.connection.ConnectionFactory;
import org.omm.inftastructure.connection.MySqlFactory;
import org.omm.inftastructure.connection.PostgreSqlFactory;
import org.omm.inftastructure.repositoryImpl.BookRepositoryImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
        if (connections == null) {
            connections = new ArrayList<>(Configuration.credentials.size());
            ConnectionCredentials postgres = Configuration.credentials.get(0);
            ConnectionFactory postgreSqlFactory = new PostgreSqlFactory(postgres.getUsername(), postgres.getPassword(), postgres.getDatabaseName());
            ConnectionCredentials mysql = Configuration.credentials.get(1);
            ConnectionFactory mySqlFactory = new MySqlFactory(mysql.getUsername(), mysql.getPassword(), mysql.getDatabaseName());
            connections.add(postgreSqlFactory.getConnection());
            connections.add(mySqlFactory.getConnection());
        }
        setUpTables(connections);
        return connections;
    }

    private void setUpTables(List<Connection> connections) {
        String query = "CREATE TABLE IF NOT EXISTS book \n" +
                "(\n" +
                "\tid BIGINT NOT NULL,\n" +
                "\tauthor_id BIGINT NOT NULL,\n" +
                "\ttitle VARCHAR(50) NOT NULL,\n" +
                "\tdeleted BOOLEAN NOT NULL,\n" +
                "\tPRIMARY KEY (id)\n" +
                ");";
        for (Connection connection : connections) {
            try {
                PreparedStatement statement = connection.prepareStatement(query);
                statement.execute();
            } catch (Exception e) {
                System.out.println(e);
                System.exit(0);
            }
        }
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
