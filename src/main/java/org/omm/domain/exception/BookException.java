package org.omm.domain.exception;

import lombok.Getter;
import org.omm.domain.model.Status;

@Getter
public abstract class BookException extends Exception {

    private final String message;

    private final Status status;

    public BookException(String message, Status status) {
        super(message);
        this.message = message;
        this.status = status;
    }

    @Override
    public String toString() {
        return "BookException!\n" + "message: " + message +
                "\nstatus: " + status + "\n";
    }
}
