package org.omm.inftastructure.connection;

import java.sql.Connection;

// Template
public abstract class ConnectorTemplate {

    protected volatile static Connection connection;
    protected final String username;
    protected final String password;
    protected final String databaseName;

    public ConnectorTemplate(String username, String password, String databaseName) {
        this.username = username;
        this.password = password;
        this.databaseName = databaseName;
    }

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
