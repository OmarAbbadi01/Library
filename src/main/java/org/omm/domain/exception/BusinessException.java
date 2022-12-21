package org.omm.domain.exception;

import org.omm.domain.model.Status;

public class BusinessException extends BookException {

    public BusinessException(String message, Status status) {
        super(message, status);
    }
}
