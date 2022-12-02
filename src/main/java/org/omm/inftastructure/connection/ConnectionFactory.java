package org.omm.inftastructure.connection;

import java.sql.Connection;

// Factory Method
public abstract class ConnectionFactory {

    private final String username;
    private final String password;
    private final String databaseName;

    public ConnectionFactory() {
        this.username = "library";
        this.password = "root";
        this.databaseName = "library";
    }

    public abstract Connection createConnection(); // create connection with DB server, using JDBC connection

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getDatabaseName() {
        return databaseName;
    }
}
