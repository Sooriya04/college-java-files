import java.util.ArrayList;
import java.util.Scanner;

class Book {
    String title;
    String author;
    boolean isAvailable;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isAvailable = true;
    }

    public void displayInfo() {
        System.out.println("Title: " + title + ", Author: " + author + ", Available: " + (isAvailable ? "Yes" : "No"));
    }
}

class User {
    String name;

    public User(String name) {
        this.name = name;
    }

    public void viewBooks(ArrayList<Book> books) {
        System.out.println("Available books:");
        for (Book book : books) {
            book.displayInfo();
        }
    }
}

class Member extends User {

    public Member(String name) {
        super(name);
    }

    public void borrowBook(ArrayList<Book> books, String bookTitle) {
        for (Book book : books) {
            if (book.title.equalsIgnoreCase(bookTitle) && book.isAvailable) {
                book.isAvailable = false;
                System.out.println(name + " borrowed: " + bookTitle);
                return;
            }
        }
        System.out.println("Book not available or not found.");
    }

    public void returnBook(ArrayList<Book> books, String bookTitle) {
        for (Book book : books) {
            if (book.title.equalsIgnoreCase(bookTitle) && !book.isAvailable) {
                book.isAvailable = true;
                System.out.println(name + " returned: " + bookTitle);
                return;
            }
        }
        System.out.println("Book not found.");
    }
}

class Librarian extends User {

    public Librarian(String name) {
        super(name);
    }

    public void addBook(ArrayList<Book> books, String title, String author) {
        books.add(new Book(title, author));
        System.out.println("Book added: " + title + " by " + author);
    }

    public void removeBook(ArrayList<Book> books, String title) {
        for (Book book : books) {
            if (book.title.equalsIgnoreCase(title)) {
                books.remove(book);
                System.out.println("Book removed: " + title);
                return;
            }
        }
        System.out.println("Book not found.");
    }
}

public class LibraryManagement {

    public static void main(String[] args) {
        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book("Harry Potter", "J.K. Rowling"));
        books.add(new Book("Lord of the Rings", "J.R.R. Tolkien"));

        Scanner scanner = new Scanner(System.in);

        Librarian librarian = new Librarian("Alice");
        Member member = new Member("Bob");

        boolean exit = false;
        while (!exit) {
            System.out.println("\n1. View Books (User)");
            System.out.println("2. Borrow Book (Member)");
            System.out.println("3. Return Book (Member)");
            System.out.println("4. Add Book (Librarian)");
            System.out.println("5. Remove Book (Librarian)");
            System.out.println("6. Exit");
            System.out.print("Enter The Choice Here: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the leftover newline character

            switch (choice) {
                case 1:
                    member.viewBooks(books);
                    break;
                case 2:
                    System.out.println("Enter book title to borrow:");
                    String borrowTitle = scanner.nextLine();
                    member.borrowBook(books, borrowTitle);
                    break;
                case 3:
                    System.out.println("Enter book title to return:");
                    String returnTitle = scanner.nextLine();
                    member.returnBook(books, returnTitle);
                    break;
                case 4:
                    System.out.println("Enter book title:");
                    String addTitle = scanner.nextLine();
                    System.out.println("Enter author:");
                    String addAuthor = scanner.nextLine();
                    librarian.addBook(books, addTitle, addAuthor);
                    break;
                case 5:
                    System.out.println("Enter book title to remove:");
                    String removeTitle = scanner.nextLine();
                    librarian.removeBook(books, removeTitle);
                    break;
                case 6:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
        scanner.close();
    }
}
//javac LibraryManagement.java
//java LibraryManagement