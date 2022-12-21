package org.omm.application.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ConnectionCredentials {
    private final String username;
    private final String password;
    private final String databaseName;
}
