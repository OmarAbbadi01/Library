package org.omm.inftastructure.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Book {

    private Long id;

    private Long authorId;

    private String title;

    private boolean deleted = false;

    public Book(Long id, Long authorId, String title) {
        this.id = id;
        this.authorId = authorId;
        this.title = title;
    }
}
