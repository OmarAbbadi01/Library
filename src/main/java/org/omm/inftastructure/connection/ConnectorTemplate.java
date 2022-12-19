package org.omm.inftastructure.connection;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Connection;

// Template
@AllArgsConstructor
@Getter
public abstract class ConnectorTemplate {

    protected volatile static Connection connection;
    protected final String username;
    protected final String password;
    protected final String databaseName;

    public Connection getConnection() {
        if (connection == null) {
            synchronized (ConnectorTemplate.class) {
                if (connection == null) {
                    try {
                        createConnection();
                    } catch (Exception e) {
                        System.out.println("EXCEPTION ! " + e);
                    }
                }
            }
        }
        return connection;
    }

    protected abstract void createConnection() throws Exception;

}
