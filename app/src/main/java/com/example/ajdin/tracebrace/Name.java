package com.example.ajdin.tracebrace;

public class Name {
    private String firstName;
    public void setFirstName(String firstName) {this.firstName = firstName;}
    public String getFirstName() {return this.firstName;}

    private String lastName;
    public void setLastName(String lastName) {this.lastName = lastName;}
    public String getLastName() {return this.lastName;}

    Name() {
        firstName = new String();
        lastName = new String();
    }

    public String fullName() {return firstName + " " + lastName;}
}
