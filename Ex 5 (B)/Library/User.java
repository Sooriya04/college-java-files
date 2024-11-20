package Library;

import java.util.Scanner;

public abstract class User {
    private String name;
    private String id;
    private String password;

    public User(String name, String id, String password) {
        this.name = name;
        this.id = id;
        this.password = password;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Getter methods for id and password
    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void viewBooks(Book[] books) {
        System.out.println("Available books:");
        for (Book book : books) {
            if (book != null) {
                book.displayInfo();
            }
        }
    }

    public abstract void performAction(Book[] books, Scanner scanner);
}
