package com.example.ajdin.tracebrace;

import java.util.Scanner;

public class Email {
    private String email;

    public void setEmail(String email) {this.email = email;}
    public String getEmail() {return this.email;}

    Email() {
        email = new String();
    }

    public boolean isValid() {
        if (email.indexOf('@') == -1)
            return false;
        return true;
    }

    public boolean exists() throws Exception{
        java.io.File file = new java.io.File("/storage/emulated/0/data_base.csv");
       Scanner input = new Scanner(file);

        String s = new String();
        int a;
        while (input.hasNext()) {
            s = input.nextLine();
            if (s.equals("")) {
                a = s.indexOf(';', s.indexOf(';') + 1);
                System.out.println(a);
                s = s.substring(a + 2, s.indexOf(';', a + 1) - 2);
            }
            if (s.equals(email))
                return true;
        }
        return false;
    }
}
