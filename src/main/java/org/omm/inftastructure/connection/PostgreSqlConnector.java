package org.omm.inftastructure.connection;

import java.sql.DriverManager;

public class PostgreSqlConnector extends ConnectorTemplate {

    public PostgreSqlConnector(String username, String password, String databaseName) {
        super(username, password, databaseName);
    }

    @Override
    public void createConnection() throws Exception {
        Class.forName("org.postgresql.Driver");
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", this.getUsername(), this.getPassword());
    }
}
