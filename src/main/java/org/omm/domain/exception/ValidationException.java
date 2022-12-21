package org.omm.domain.exception;

import org.omm.domain.model.Status;

public class ValidationException extends BookException {

    public ValidationException(String message, Status status) {
        super(message, status);
    }
}
