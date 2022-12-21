package org.omm.application.validator;

import org.omm.domain.model.BookDto;

public interface BookValidator {

    void validateForCreateUpdate(BookDto bookDto) throws Exception;

    void validateId(Long id) throws Exception;

}
