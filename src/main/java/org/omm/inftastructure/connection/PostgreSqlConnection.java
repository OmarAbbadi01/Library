package org.omm.inftastructure.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class PostgreSqlConnection extends ConnectionFactory {

    private volatile static Connection connection = null;
    ConnectionFactory myFactory;

    @Override
    public Connection createConnection() {

        try {
            if (connection == null) {
                synchronized (PostgreSqlConnection.class) {
                    if (connection == null) {
                        Class.forName("org.postgresql.Driver");
                        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", this.getUsername(), this.getPassword());
                    }
                }
            }

        } catch (Exception e) {
            System.out.print(e);
        }

        return connection;
    }
}
