package org.openjfx.model;

public class User {

    private String username;
    private String password;
    private String lastName;
    private String firstName;

    public void User(String userName, String pass, String fName, String lName) {
        this.username = userName;
        this.password = pass;
        this.firstName = fName;
        this.lastName = lName;
    }


}
