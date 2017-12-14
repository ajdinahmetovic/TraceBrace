package com.example.ajdin.tracebrace;


import java.io.FileNotFoundException;
import java.util.Scanner;

public class User {
    public enum Info {
        NAME(1), EMAIL(2), PASSWORD(4), ALL(7);

        private final int value;

        Info(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    private Name name;

    public void setName(Name name) {
        this.name = name;
    }

    public void setName(String fullName) {
        this.name.setFirstName(fullName.substring(0, fullName.indexOf(' ')));
        name.setLastName(fullName.substring(fullName.indexOf(' ') + 1, fullName.length()));
    }

    public Name getName() {
        return this.name;
    }

    private Email email;

    public void setEmail(String email) {
        this.email.setEmail(email);
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public Email getEmail() {
        return this.email;
    }

    private Password password;

    public void setPassword(String password) {
        this.password.setPassword(password);
    }

    public void setPassword(Password password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password.getPassword();
    }

    public User() {
        name = new Name();
        email = new Email();
        password = new Password();
    }

    public User(String name, String email, String password) {
        this.name = new Name();
        this.email = new Email();
        this.password = new Password();

        setName(name);
        setEmail(email);
        setPassword(password);
    }

    public User find(String filePath, int info) throws FileNotFoundException {
        java.io.File file = new java.io.File(filePath);
        Scanner input = new Scanner(file);
        User temp = new User();

        while (input.hasNext()) {
            temp = toUser(input.nextLine());

            if ((!((info & Info.NAME.getValue()) == Info.NAME.getValue())) || this.name.fullName().equals(temp.getName().fullName())) {
                if ((!((info & Info.EMAIL.getValue()) == Info.EMAIL.getValue())) || this.email.getEmail().equals(temp.getEmail().getEmail())) {
                    if ((!((info & Info.PASSWORD.getValue()) == Info.PASSWORD.getValue())) || this.password.getPassword().equals(temp.getPassword())) {
                        return temp;
                    }
                }
            }
        }
        return new User("00 00", "0", "0");
    }

    public User toUser(String line) {
        User user = new User();

        user.setName(line.substring(1, line.indexOf(';') - 1) + " " + line.substring(line.indexOf(';') + 2, line.indexOf(';', line.indexOf(';') + 1) - 1));
        line = line.substring(line.indexOf(';', line.indexOf(';') + 1) + 1);

        user.setEmail((line.substring(1, line.indexOf(';') - 1)));
        line = line.substring(line.indexOf(';') + 1);

        user.setPassword((line.substring(1, line.indexOf(';') - 1)));

        return user;
    }

    public void writeToCSV(String filePath) {
        String fileContant = new String();
        try {
            {
                java.io.File file = new java.io.File(filePath);
                Scanner scanner = new Scanner(file);
                while (scanner.hasNext())
                    fileContant += scanner.nextLine() + "\n";
            }
            java.io.PrintWriter output = new java.io.PrintWriter(filePath);
            output.print(fileContant + "\"" + name.getFirstName() + "\";\"" + name.getLastName() + "\";\"" + getEmail().getEmail() + "\";\"" + getPassword() + "\";\n");
            output.close();
        } catch (Exception e) {
        }
    }

    public boolean equals(User u) {
        if (this.getName().fullName().equals(u.getName().fullName()))
            if (this.getEmail().getEmail().equals(u.getEmail().getEmail()))
                if (this.getPassword().equals(u.getPassword()))
                    return true;
        return false;
    }
}