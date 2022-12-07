package org.omm;

import org.omm.inftastructure.connection.ConnectorTemplate;
import org.omm.inftastructure.connection.PostgreSqlConnector;
import org.omm.inftastructure.dao.BookDao;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Main {
    public static void main(String[] args) throws Exception {
        ConnectorTemplate connector = new PostgreSqlConnector("library", "root", "library");
        Connection connection = connector.getConnection();

        String createBookTableQuery = "CREATE TABLE IF NOT EXISTS book " +
                "(id INTEGER NOT NULL," +
                "author_id INTEGER NOT NULL," +
                "title VARCHAR(50) NOT NULL," +
                "PRIMARY KEY (id) )";
        try {
            PreparedStatement statement = connection.prepareStatement(createBookTableQuery);
            statement.execute();
            statement.close();
        } catch (Exception e) {
            System.out.println("EXCEPTION ! " + e);
        }

        BookDao dao = new BookDao(connection);
        System.out.println(dao.findById(1L));

    }
}
