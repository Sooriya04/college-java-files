package Library;
//import java.util.Scanner;
public class Student extends User {

    public Student(String id, String password) {
        super(id, password);
    }

    @Override
    public void viewBooks(Book[] books) {
        System.out.println("Available books:");
        for (Book book : books) {
            if (book != null) {
                book.displayInfo();
            }
        }
    }

    @Override
    public void borrowBook(Book[] books, String title) {
        for (Book book : books) {
            if (book != null && book.title.equalsIgnoreCase(title) && book.isAvailable) {
                book.isAvailable = false;
                System.out.println("You have borrowed: " + title);
                return;
            }
        }
        System.out.println("Book is not available or not found.");
    }

    @Override
    public void returnBook(Book[] books, String title) {
        for (Book book : books) {
            if (book != null && book.title.equalsIgnoreCase(title) && !book.isAvailable) {
                book.isAvailable = true;
                System.out.println("You have returned: " + title);
                return;
            }
        }
        System.out.println("This book was not borrowed by you.");
    }
}
