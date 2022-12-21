package org.omm.inftastructure.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class PostgreSqlFactory extends ConnectionFactory {

    private static volatile Connection connection;

    public PostgreSqlFactory(String username, String password, String databaseName) {
        super(username, password, databaseName);
    }

    @Override
    public Connection getConnection() {
        if (connection == null) {
            synchronized (PostgreSqlFactory.class) {
                if (connection == null) {
                    try {
                        Class.forName("org.postgresql.Driver");
                        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + this.getDatabaseName(), this.getUsername(), this.getPassword());
                    } catch (Exception e) {
                        System.out.println("EXCEPTION ! Couldn't Create Connection " + e);
                    }
                }
            }
        }
        return connection;
    }


}
