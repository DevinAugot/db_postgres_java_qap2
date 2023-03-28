import com.keyin.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
public class Main {
    public static void main(String[] args) throws SQLException {

        Dbfunctions db = new Dbfunctions();
        Connection conn = db.PostgresClient("Keyin", "postgres", "Keyin2021");

        // Creating Tables in postgres
        db.CreateBookTable(conn, "Book");
        db.CreateAuthorTable(conn, "Author");
        db.CreatePatronTable(conn, "Patron");
        db.CreateBookCheckoutTable(conn, "book_checkout");

        // SQL QUERY'S
        String GET_ALL_BOOKS_QUERY = "SELECT * FROM Book";

        String GET_ALL_AUTHORS_QUERY = "SELECT * FROM Author";

        String GET_ALL_PATRON_QUERY = "SELECT * FROM Patron";

        String GET_ALL_BOOKS_CHECKED_OUT_QUERY = "SELECT c.checkout_id, p.patron_id, b.book_id, c.checkout_date, c.due_date, c.return_date, p.name " +
                "FROM book_checkout c " +
                "JOIN patron p ON c.patron_id = p.patron_id " +
                "JOIN book b ON c.book_id = b.book_id " +
                "ORDER BY p.name";



        // initializing array lists
        List<Books> listOfBooks = new ArrayList<>();
        List<Author> listOfAuthors = new ArrayList<>();
        List<Patron> listOfPatrons = new ArrayList<>();
        List<BooksCheckedOut> listOfBooksCheckedOut = new ArrayList<>();

        // Book table inserts
        db.insertIntoBook(conn, 1, 1, "Page Inc.", "709-18765-03", "Harry potter & Philosopher's Stone");
        db.insertIntoBook(conn, 2, 2, "Horror Inc.", "710-5678-03", "The Shining");

        // Author table inserts
        db.insertIntoAuthor(conn, 1, "JK Rowling", Date.valueOf("1973-08-23"));
        db.insertIntoAuthor(conn, 2, "Stephen King", Date.valueOf("1947-02-21"));

        // Patrons table Inserts
        db.insertIntoPatron(conn, 1, "Devin Augot", "123 easy st", "709-280-3944");
        db.insertIntoPatron(conn, 2, "Allison Butler", "123 easy st", "709-579-5678");

        // Book Checkout table being populated from other tables here

        db.insertIntoBookCheckoutFromTables(conn, 1, 1, 1, 1, Date.valueOf("2023-03-27"), Date.valueOf("2023-04-03"),
                Date.valueOf("2023-04-5"));
        db.insertIntoBookCheckoutFromTables(conn, 2, 2, 2, 2, Date.valueOf("2023-03-28"), Date.valueOf("2023-04-06"),
                Date.valueOf("2023-04-6"));




        //      QUERY TO GET ALL AUTHORS
        ResultSet resultSetForAllAuthors = db.queryDatabase(conn, GET_ALL_AUTHORS_QUERY);
        while (resultSetForAllAuthors.next()) {
            Author author = new Author(resultSetForAllAuthors.getInt("author_id"), resultSetForAllAuthors.getString("name"),
                    resultSetForAllAuthors.getDate("dob"));
            listOfAuthors.add(author);
        }

        //      QUERY TO GET ALL Books
        ResultSet resultSetForAllBooks = db.queryDatabase(conn, GET_ALL_BOOKS_QUERY);
        while (resultSetForAllBooks.next()) {
            Books book = new Books(resultSetForAllBooks.getInt("book_id"),
                    resultSetForAllBooks.getString("title"), resultSetForAllBooks.getInt("author_id"), resultSetForAllBooks.getString("publisher"), resultSetForAllBooks.getString("isbn")
            );
            listOfBooks.add(book);
        }

        //      QUERY TO GET ALL Patrons
        ResultSet resultSetForAllPatrons = db.queryDatabase(conn, GET_ALL_PATRON_QUERY);
        while (resultSetForAllPatrons.next()) {
            Patron patron = new Patron(resultSetForAllPatrons.getInt("patron_id"), resultSetForAllPatrons.getString("name"), resultSetForAllPatrons.getString("address"), resultSetForAllPatrons.getString("phone_number")
            );
            listOfPatrons.add(patron);
        }



        //      QUERY TO GET ALL Checked out Books to display in terminal
        ResultSet resultSetForBooksCheckedOut = db.queryDatabase(conn,GET_ALL_BOOKS_CHECKED_OUT_QUERY);
        while (resultSetForBooksCheckedOut.next()){
            BooksCheckedOut bookCheckedOut = new BooksCheckedOut(resultSetForBooksCheckedOut.getInt("checkout_id"),
                    resultSetForBooksCheckedOut.getInt("patron_id"),resultSetForBooksCheckedOut.getInt("book_id"), resultSetForBooksCheckedOut.getDate("checkout_date"), resultSetForBooksCheckedOut.getDate("due_date"),
                    resultSetForBooksCheckedOut.getDate("return_date")
                     );
                    listOfBooksCheckedOut.add(bookCheckedOut); }




        // Implementing New Library DataBase Management system object

        LibraryDBManagement libraryDBManagement = new LibraryDBManagement();

        libraryDBManagement.setBooks(listOfBooks);
        libraryDBManagement.setAuthors(listOfAuthors);
        libraryDBManagement.setPatrons(listOfPatrons);
        libraryDBManagement.setCheckedOutBooks(listOfBooksCheckedOut);


        // Iterating list of Authors
        libraryDBManagement.setAuthors(listOfAuthors);
        List<Author> results = libraryDBManagement.getAuthors();
        int AuthorCounter = 0;
        System.out.println("*****{List Of Authors}*****");
        for (Author authors : results) {
            AuthorCounter+=1;
            System.out.println(AuthorCounter + "." + authors.getName() + "\n");

        }

        // Iterating list of Books
        libraryDBManagement.setBooks(listOfBooks);
        List<Books> resultsBook = libraryDBManagement.getBooks();
        int bookCounter = 0;
        System.out.println("*****{List Of Books}*****");
        for (Books item : resultsBook) {

            bookCounter+=1;
            System.out.println(bookCounter + "." + item.getTitle()+ "\n");
        }

        // Iterating list of Patrons
        libraryDBManagement.setPatrons(listOfPatrons);
        List<Patron> resultsPatron = libraryDBManagement.getPatrons();
        int patronCounter=0;
        System.out.println("*****{List Of Patrons}*****");
        for (Patron patronItem : resultsPatron) {
            patronCounter+=1;
            System.out.println(patronCounter + "." + patronItem.getName() + " " + patronItem.getAddress() + "\n");
        }



        // Getting all checked out books
        libraryDBManagement.setCheckedOutBooks(listOfBooksCheckedOut);
        List<BooksCheckedOut> resultsBooksCheckedOut = libraryDBManagement.getCheckedOutBooks();
       int checkoutBookCtr = 0;
        System.out.println("*****{List Of Checked Out Books}*****");
        for (BooksCheckedOut bookItem : resultsBooksCheckedOut) {
            checkoutBookCtr += 1;
            System.out.println(checkoutBookCtr + "." + bookItem + "\n");

        }

        System.out.println("Book Search by id: "+ libraryDBManagement.getBooksById(2)); // works for search


        }
    }



