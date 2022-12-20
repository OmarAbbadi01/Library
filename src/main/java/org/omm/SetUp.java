package org.omm;

import org.omm.application.BookController;
import org.omm.application.BookValidator;
import org.omm.application.BookValidatorImpl;
import org.omm.domain.repository.BookRepository;
import org.omm.domain.service.BookService;
import org.omm.domain.service.BookServiceImpl;
import org.omm.domain.service.Subject;
import org.omm.inftastructure.connection.ConnectorTemplate;
import org.omm.inftastructure.connection.MysqlConnector;
import org.omm.inftastructure.connection.PostgreSqlConnector;
import org.omm.inftastructure.dao.BookDao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class SetUp {

    private List<Connection> setUpConnections() {
        ConnectorTemplate postgreSqlConnector = new PostgreSqlConnector("library", "root", "library");
        //ConnectorTemplate mysqlConnector = new MysqlConnector("library", "root", "library");
        List<Connection> connections = new ArrayList<>();
        connections.add(postgreSqlConnector.getConnection());
        //connections.add(mysqlConnector.getConnection());
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

    public Subject setUpSubject(){
        return new BookServiceImpl(setUpRepository());
    }

    public Dashboard setUpDashboard() throws Exception{
        return new Dashboard(setUpSubject(),setUpService());
    }
}
