package org.omm.inftastructure.connection;

import java.sql.DriverManager;

public class MysqlConnector extends ConnectorTemplate {

    public MysqlConnector(String username, String password, String databaseName) {
        super(username, password, databaseName);
    }

    @Override
    public void createConnection() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:MySql://localhost:3306/mysql", this.getUsername(), this.getPassword());
    }
}
