package com.keyin;
import java.sql.*;
import java.sql.Date;

public class Dbfunctions {

    public Connection PostgresClient(String dbName, String user, String password) {

        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;

        try {
            // Load the PostgresSQL Driver
            Class.forName("org.postgresql.Driver");

            //Establish a connection to the database
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + dbName, user, password);
            if (connection != null) {
                System.out.println("connection established");
            } else {
                System.out.println("Connection failed");
            }

            // catch any exception errors
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);

        }
        return connection;
    }

    public void CreateBookTable(Connection connection, String table_name) {
        Statement statement;
        try {
            // Execute SQL query
            String query = "CREATE TABLE " + table_name + "(book_id SERIAL PRIMARY KEY,title VARCHAR(200) NOT NULL," +
                    "author_id INT NOT NULL,publisher VARCHAR(100),isbn VARCHAR(20))";


            statement = connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Table Created");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        {
        }

    }


    public void CreateAuthorTable(Connection connection, String table_name) {
        Statement statement;
        try {
            // Execute SQL query
            String query = "CREATE TABLE " + table_name + "(author_id INT NOT NULL PRIMARY KEY,name " +
                    "VARCHAR(50) NOT NULL," +
                    " dob DATE NOT NULL)";

            statement = connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Author Table Created");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        {
        }

    }

    public void CreatePatronTable(Connection connection, String table_name) {
        Statement statement;
        try {
            // Execute SQL query
            String query = "CREATE TABLE " + table_name + "( patron_id INT NOT NULL PRIMARY KEY,name " +
                    "VARCHAR(50) NOT NULL," +
                    " address VARCHAR(50) NOT NULL, phone_number VARCHAR(50) NOT NULL)";

            statement = connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Patron Table Created");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        {
        }

    }

    public void CreateBookCheckoutTable(Connection connection, String table_name) {
        Statement statement;
        try {
            // Execute SQL query
            String query = "CREATE TABLE " + table_name + "( checkout_id INT NOT NULL PRIMARY KEY,author_id " +
                    "INT NOT NULL," +
                    "patron_id INT NOT NULL, book_id INT NOT NULL, checkout_date DATE NOT NULL, due_date DATE NOT" +
                    " NULL, return_date DATE NOT NULL)";

            statement = connection.createStatement();
            statement.executeUpdate(query);
            System.out.println(" Book checkout Table Created");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        {
        }
    }

    public ResultSet queryDatabase(Connection conn,String query) {
        ResultSet rs = null;
        try {
            Statement statement = conn.createStatement();
            rs = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public void insertIntoBook(Connection conn, int book_id, int author_id, String publisher, String isbn, String title) {
        PreparedStatement statement = null;
        try {
            String query = "INSERT INTO book (book_id, author_id, publisher, isbn, title) VALUES (?, ?, ?, ?, ?)";
            statement = conn.prepareStatement(query);
            statement.setInt(1, book_id);
            statement.setInt(2, author_id);
            statement.setString(3, publisher);
            statement.setString(4, isbn);
            statement.setString(5, title);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Book has been added to database");
            } else {
                System.out.println("ERROR: Book could not be added to database, Check SQL syntax");
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public void insertIntoAuthor(Connection conn, int author_id, String name, Date dob) {
        PreparedStatement statement = null;
        try {
            String query = "INSERT INTO author (author_id, name, dob) VALUES (?, ?, ?)";
            statement = conn.prepareStatement(query);
            statement.setInt(1, author_id);
            statement.setString(2, name);
            statement.setDate(3, dob);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Author has been added");
            } else {
                System.out.println("ERROR: Author could not be added, Check SQL syntax");
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
    public void insertIntoPatron(Connection conn, int patron_id, String name, String address, String phone_number) {
        PreparedStatement statement = null;
        try {
            String query = "INSERT INTO patron (patron_id, name, address, phone_number) VALUES (?, ?, ?, ?)";
            statement = conn.prepareStatement(query);
            statement.setInt(1, patron_id);
            statement.setString(2, name);
            statement.setString(3, address);
            statement.setString(4,phone_number);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Author has been added");
            } else {
                System.out.println("ERROR: Author could not be added, Check SQL syntax");
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public void insertIntoBookCheckoutFromTables(Connection conn, int checkout_id, int author_id, int patron_id, int book_id, Date checkout_date, Date due_date, Date return_date) {
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            String query = "INSERT INTO book_checkout (checkout_id, book_id, author_id, patron_id, checkout_date, " +
                    "due_date, return_date) " +
                    "SELECT ?, b.book_id, a.author_id, p.patron_id, ?, ?, ? " +
                    "FROM Book b " +
                    "JOIN Author a ON b.author_id = a.author_id " +
                    "JOIN Patron p ON p.patron_id = ? " +
                    "WHERE b.book_id = ? AND a.author_id = ?";

            statement = conn.prepareStatement(query);
            statement.setInt(1, checkout_id);
            statement.setDate(2, checkout_date);
            statement.setDate(3, due_date);
            statement.setDate(4, return_date);
            statement.setInt(5, patron_id);
            statement.setInt(6, book_id);
            statement.setInt(7, author_id);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Book checkout has been added");
            } else {
                System.out.println("ERROR: Book checkout could not be added");
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (statement != null) {
                    statement.close();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}



