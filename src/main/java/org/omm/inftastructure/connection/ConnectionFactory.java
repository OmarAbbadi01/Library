package org.omm.inftastructure.connection;

import lombok.Getter;

import java.sql.Connection;

@Getter
public abstract class ConnectionFactory {

    protected final String username;
    protected final String password;
    protected final String databaseName;

    public ConnectionFactory(String username, String password, String databaseName) {
        this.username = username;
        this.password = password;
        this.databaseName = databaseName;
    }

    public abstract Connection getConnection();

}
