package com.keyin;
import java.util.List;

public class LibraryDBManagement {
    private List<Books> books;
    private List<Author> authors;
    private List<Patron> patrons;
    private List<BooksCheckedOut> checkedOutBooks;

    public List<Books> getBooks() {
        return books;
    }

    public void setBooks(List<Books> books) {
        this.books = books;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setCheckedOutBooks(List<BooksCheckedOut> checkedOutBooks) {
        this.checkedOutBooks = checkedOutBooks;
    }

    public List<BooksCheckedOut> getCheckedOutBooks() {
        return checkedOutBooks;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public List<Patron> getPatrons() {
        return patrons;
    }

    public void setPatrons(List<Patron> patrons) {
        this.patrons = patrons;
    }

    public Books getBooksById(int id) {
        for (Books book : books) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }
}

