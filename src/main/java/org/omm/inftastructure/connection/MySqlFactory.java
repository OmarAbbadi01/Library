package org.omm.inftastructure.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySqlFactory extends ConnectionFactory {

    private static volatile Connection connection;

    public MySqlFactory(String username, String password, String databaseName) {
        super(username, password, databaseName);
    }

    @Override
    public Connection getConnection() {
        if (connection == null) {
            synchronized (MySqlFactory.class) {
                if (connection == null) {
                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        connection = DriverManager.getConnection("jdbc:MySql://localhost:3306/" + this.getDatabaseName(), this.getUsername(), this.getPassword());
                    } catch (Exception e) {
                        System.out.println("EXCEPTION ! Couldn't Create Connection " + e);
                    }
                }
            }
        }
        return connection;
    }

}
