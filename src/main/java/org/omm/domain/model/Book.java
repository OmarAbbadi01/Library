package org.omm.domain.model;

public class Book {

    private Long id;

    private Long authorId;

    private String title;

    public Book() {
    }

    public Book(Long id, Long authorId, String title) {
        this.id = id;
        this.authorId = authorId;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", authorId=" + authorId +
                ", title='" + title + '\'' +
                '}';
    }
}
