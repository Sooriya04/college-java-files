package Library;

import java.util.Scanner;

public class Librarian extends User {
    public Librarian(String name, String id, String password) {
        super(name, id, password);
    }

    @Override
    public void performAction(Book[] books, Scanner scanner) {
        System.out.println("\n1. Add Book");
        System.out.println("2. Remove Book");
        System.out.print("Enter the choice here: ");
        int action = scanner.nextInt();
        scanner.nextLine();

        if (action == 1) {
            System.out.println("Enter book title to add:");
            String title = scanner.nextLine();
            System.out.println("Enter author:");
            String author = scanner.nextLine();
            addBook(books, title, author);
        } else if (action == 2) {
            System.out.println("Enter book title to remove:");
            String title = scanner.nextLine();
            removeBook(books, title);
        } else {
            System.out.println("Invalid action.");
        }
    }

    public void addBook(Book[] books, String title, String author) {
        for (int i = 0; i < books.length; i++) {
            if (books[i] == null) {
                books[i] = new Book(title, author);
                System.out.println("Book added: " + title + " by " + author);
                return;
            }
        }
        System.out.println("Library is full. Cannot add more books.");
    }

    public void removeBook(Book[] books, String title) {
        for (int i = 0; i < books.length; i++) {
            if (books[i] != null && books[i].title.equalsIgnoreCase(title)) {
                System.out.println("Book removed: " + title);
                books[i] = null;
                return;
            }
        }
        System.out.println("Book not found.");
    }
}
