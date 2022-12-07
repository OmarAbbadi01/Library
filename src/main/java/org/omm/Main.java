package org.omm;

import org.omm.inftastructure.connection.ConnectorTemplate;
import org.omm.inftastructure.connection.PostgreSqlConnector;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        ConnectorTemplate connector1 = new PostgreSqlConnector("library", "root", "library");
        ConnectorTemplate connector2 = new PostgreSqlConnector("library", "root", "library");
        Connection connection1 = connector1.getConnection();
        Connection connection2 = connector2.getConnection();
        System.out.println(connection1.equals(connection2)); // should return true
    }
}
