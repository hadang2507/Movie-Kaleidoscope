package org.openjfx.model;

/**
 * USER DETAIL
 * @author Dang Ngan Ha
 */
public class User {

    private String username;
    private String password;
    private String lastName;
    private String firstName;

    public User(String userName, String pass, String fName, String lName) {
        this.username = userName;
        this.password = pass;
        this.firstName = fName;
        this.lastName = lName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
