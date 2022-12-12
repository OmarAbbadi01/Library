package org.omm;

import org.omm.application.BookController;
import org.omm.application.BookValidator;
import org.omm.application.BookValidatorImpl;
import org.omm.domain.repository.BookRepository;
import org.omm.domain.service.BookService;
import org.omm.domain.service.BookServiceImpl;
import org.omm.inftastructure.connection.ConnectorTemplate;
import org.omm.inftastructure.connection.MysqlConnector;
import org.omm.inftastructure.connection.PostgreSqlConnector;
import org.omm.inftastructure.dao.BookDao;

import java.sql.Connection;

public class SetUp {

    private Connection[] setUpConnections() {
        ConnectorTemplate mysqlConnector = new MysqlConnector("library", "root", "library");
        ConnectorTemplate postgreSqlConnector = new PostgreSqlConnector("library", "root", "library");
        Connection[] connections = new Connection[2];
        connections[0] = postgreSqlConnector.getConnection();
        connections[1] = mysqlConnector.getConnection();
        return connections;
    }

    private BookRepository setUpRepository() {
        return new BookDao(setUpConnections());
    }

    private BookService setUpService() {
        return new BookServiceImpl(setUpRepository());
    }

    private BookValidator setUpValidator() {
        return new BookValidatorImpl();
    }

    public BookController setUpController() {
        return new BookController(setUpService(), setUpValidator());
    }
}
