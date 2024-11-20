package library;
import java.util.Scanner;
public class Member extends User {
    private Book[] borrowedBooks = new Book[5];
    private int borrowedCount = 0;
    public Member(String name, String id, String password) {
        super(name, id, password);
    }
    @Override
    public void performAction(Book[] books, Scanner scanner) {
        System.out.println("\n1. Borrow Book");
        System.out.println("2. Return Book");
        System.out.print("Enter the choice here: ");
        int action = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (action == 1) {
            System.out.println("Enter book title to borrow:");
            String title = scanner.nextLine();
            borrowBook(books, title);
        } else if (action == 2) {
            System.out.println("Enter book title to return:");
            String title = scanner.nextLine();
            returnBook(title);
        } else {
            System.out.println("Invalid action.");
        }
    }
    public void borrowBook(Book[] books, String title) {
        for (Book book : books) {
            if (book != null && book.getTitle().equalsIgnoreCase(title) && book.isAvailable()) {
                book.setAvailable(false);
                borrowedBooks[borrowedCount++] = book;
                System.out.println(name + " borrowed: " + title);
                return;
            }
        }
        System.out.println("Book not available or not found.");
    }
    public void returnBook(String title) {
        for (int i = 0; i < borrowedCount; i++) {
            if (borrowedBooks[i] != null && borrowedBooks[i].getTitle().equalsIgnoreCase(title)) {
                borrowedBooks[i].setAvailable(true);
                borrowedBooks[i] = null;
                borrowedCount--;
                System.out.println(name + " returned: " + title);
                return;
            }
        }
        System.out.println("Book not found in your borrowed list.");
    }
}