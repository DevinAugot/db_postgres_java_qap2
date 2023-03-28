package com.keyin;


import java.sql.Date;

public class BooksCheckedOut {
  
    /* made these private final because I won't need to change anything with the BooksCheckedout
       because the list is populated from author,patrons books etc  also removed some of the setters and getters because they
       aren't being used and to clean up code base */
  
    private final int checkout_id;
    private final int book_id;
    private final int patron_id;
    private final Date return_date;
    private final Date checkout_date;
    private final Date due_date;


    public BooksCheckedOut(int checkout_id, int book_id, int patron_id, Date return_date, Date checkout_date,
                           Date due_date) {
        this.checkout_id = checkout_id;
        this.book_id = book_id;
        this.patron_id = patron_id;
        this.return_date = return_date;
        this.checkout_date = checkout_date;
        this.due_date = due_date;

    }
