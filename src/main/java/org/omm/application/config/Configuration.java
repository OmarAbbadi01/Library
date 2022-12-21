package org.omm.application.config;

import java.util.Arrays;
import java.util.List;


public class Configuration {
    // first one for PostgreSQL
    // second one for MySQL
    final static List<ConnectionCredentials> credentials = Arrays.asList(
            new ConnectionCredentials("library", "root", "library"),
            new ConnectionCredentials("library", "root12341234", "public")
    );
}
