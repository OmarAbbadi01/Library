package org.omm.inftastructure.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Book {

    private Long id;

    private Long authorId;

    private String title;

    // other attributes
    // deleted

}
