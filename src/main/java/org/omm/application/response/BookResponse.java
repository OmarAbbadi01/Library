package org.omm.application.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.omm.domain.model.Status;

@AllArgsConstructor
@Getter
public class BookResponse<T> {

    private final T t;

    private final Status status;

    @Override
    public String toString() {
        return "Status: " + status
                + "\nData: " + t.toString();
    }

}
