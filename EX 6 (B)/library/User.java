package library;
import java.io.*;
import java.util.*;
public abstract class User {
    String name;
    String id;
    String password;

    public User(String name, String id, String password) {
        this.name = name;
        this.id = id;
        this.password = password;
    }

    public void viewBooks(Book[] books) {
        System.out.println("Available books:");
        for (Book book : books) {
            if (book != null) {
                book.displayInfo();
            }
        }
    }

    public void saveUserDetails() {
        String filename = (this instanceof Librarian ? "librarian_" : "member_") + id + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("Name: " + name + "\n");
            writer.write("ID: " + id + "\n");
            writer.write("Password: " + password + "\n");
        } catch (IOException e) {
            System.out.println("Error saving user details: " + e.getMessage());
        }
    }

    public static User loadUserDetails(String id, String userType) {
        String filename = (userType.equalsIgnoreCase("librarian") ? "librarian_" : "member_") + id + ".txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String name = reader.readLine().split(": ")[1];
            String userId = reader.readLine().split(": ")[1];
            String password = reader.readLine().split(": ")[1];
            return userType.equalsIgnoreCase("librarian") ? new Librarian(name, userId, password) : new Member(name, userId, password);
        } catch (IOException e) {
            System.out.println("User file not found or error loading user: " + e.getMessage());
        }
        return null;
    }

    public abstract void performAction(Book[] books, Scanner scanner);
}
