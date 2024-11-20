import Library.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    
    private static ArrayList<Student> students = new ArrayList<>();
    private static ArrayList<Librarian> librarians = new ArrayList<>();
    private static Book[] books = new Book[100];

    public static void main(String[] args) {
        loadUsers();
        loadBooks();

        Scanner scanner = new Scanner(System.in);
        User currentUser = authenticateUser(scanner);

        if (currentUser == null) {
            System.out.println("Invalid credentials. Exiting.");
            return;
        }

        boolean exit = false;
        while (!exit) {
            System.out.println("\n1. View Books");
            System.out.println("2. Borrow Book");
            System.out.println("3. Return Book");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> currentUser.viewBooks(books);
                case 2 -> {
                    if (currentUser instanceof Student) {
                        System.out.print("Enter book title to borrow: ");
                        String title = scanner.nextLine();
                        currentUser.borrowBook(books, title);
                    } else {
                        System.out.println("Only students can borrow books.");
                    }
                }
                case 3 -> {
                    if (currentUser instanceof Student) {
                        System.out.print("Enter book title to return: ");
                        String title = scanner.nextLine();
                        currentUser.returnBook(books, title);
                    } else {
                        System.out.println("Only students can return books.");
                    }
                }
                case 4 -> exit = true;
                default -> System.out.println("Invalid choice.");
            }
        }
        scanner.close();
    }

    private static void loadUsers() {
        try (Scanner studentScanner = new Scanner(new File("File/Students.txt"));
             Scanner librarianScanner = new Scanner(new File("File/Librarians.txt"))) {
            while (studentScanner.hasNextLine()) {
                String[] data = studentScanner.nextLine().split(",");
                students.add(new Student(data[0], data[1]));
            }
            while (librarianScanner.hasNextLine()) {
                String[] data = librarianScanner.nextLine().split(",");
                librarians.add(new Librarian(data[0], data[1]));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error loading user files.");
        }
    }

    private static void loadBooks() {
        try (Scanner bookScanner = new Scanner(new File("File/Books.txt"))) {
            int i = 0;
            while (bookScanner.hasNextLine() && i < books.length) {
                String[] data = bookScanner.nextLine().split(",");
                books[i++] = new Book(data[0], data[1]);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error loading books file.");
        }
    }

    private static User authenticateUser(Scanner scanner) {
        System.out.print("Enter ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        for (Student student : students) {
            if (student.authenticate(id, password)) {
                return student;
            }
        }
        for (Librarian librarian : librarians) {
            if (librarian.authenticate(id, password)) {
                return librarian;
            }
        }
        return null;
    }
}
