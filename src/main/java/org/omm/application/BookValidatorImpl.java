package org.omm.application;

import org.omm.domain.exception.ValidationException;
import org.omm.domain.model.BookDto;
import org.omm.domain.model.Status;

public class BookValidatorImpl implements BookValidator {

    public void validateForCreateUpdate(BookDto bookDto) throws Exception {
        if (bookDto.getAuthorId() == null) {
            throw new ValidationException("Not Valid! Author_ID Can't Be Null", Status.BAD_REQUEST);
        }
        if (bookDto.getTitle() == null) {
            throw new ValidationException("Not Valid! Title Can't Be Null", Status.BAD_REQUEST);
        }
    }

    @Override
    public void validateId(Long id) throws Exception {
        if (id < 0) {
            throw new ValidationException("ID Must Be Positive", Status.BAD_REQUEST);
        }
    }
}
