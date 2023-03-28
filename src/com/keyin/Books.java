package com.keyin;

public class Books {
    private int book_id;
    private String title;
    private int author_id;
    private String publisher;
    private String isbn;

    public Books(int book_id, String title, int author_id, String publisher, String isbn) {
        this.book_id = book_id;
        this.title = title;
        this.author_id = author_id;
        this.publisher = publisher;
        this.isbn = isbn;
    }

    public int getId() {
        return book_id;
    }

    public void setId(int id) {
        this.book_id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        return "Books{" +
                "id=" + book_id +
                ", title='" + title + '\'' +
                ", author_id=" + author_id +
                ", publisher='" + publisher + '\'' +
                ", isbn='" + isbn + '\'' +
                '}';
    }
}
