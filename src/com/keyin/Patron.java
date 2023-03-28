package com.keyin;
public class Patron {
  
    /* made these private final because I won't need to change anything with the patrons
       once created for the purpose
       of this program, also removed some of the setters and getters because they aren't being used and to clean
       up code base */

    private final int id;
    private final String firstLastName;
    private final String address;
    private final String phoneNum;

    public Patron(int id, String name, String address, String phoneNum) {
        this.id = id;
        this.firstLastName = name;
        this.address = address;
        this.phoneNum = phoneNum;
    }


    public String getName() {
        return firstLastName;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "Patron{" +
                "id=" + id +
                ", name='" + firstLastName + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNum + '\'' +
                '}';
    }
}
