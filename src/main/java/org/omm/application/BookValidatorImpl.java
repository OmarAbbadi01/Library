package org.omm.application;

import org.omm.domain.model.BookDto;

public class BookValidatorImpl implements BookValidator{

    public void validateForCreateUpdate(BookDto bookDto) throws Exception{
        if (bookDto.getAuthorId() == null) {
            throw new Exception("Not Valid! Author_ID Can't Be Null");
        }
        if (bookDto.getTitle() == null) {
            throw new Exception("Not Valid! Title Can't Be Null");
        }
    }
}
