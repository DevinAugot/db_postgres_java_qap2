package com.keyin;

import java.sql.Date;

public class Author {
    /* made these private final because I won't need to change anything with the Authors
       once created for the purpose
       of this program, also removed some of the setters and getters because they aren't being used and to clean
       up code base */
    private final int id;
    private final String authorName;
    private final Date dob;

    public Author(int id, String name, Date dob) {
        this.id = id;
        this.authorName = name;
        this.dob = dob;
    }

    public String getName() {
        return authorName;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + authorName + '\'' +
                ", dob=" + dob +
                '}';
    }
}
