import java.util.*;

class Book {
    private String bookId;
    private String title;
    private String author;
    private boolean isIssued;

    public Book(String bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.isIssued = false;
    }

    public String getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isIssued() {
        return isIssued;
    }

    public void issueBook() {
        this.isIssued = true;
    }

    public void returnBook() {
        this.isIssued = false;
    }

    @Override
    public String toString() {
        return "Book ID: " + bookId + ", Title: " + title + ", Author: " + author + ", Issued: " + isIssued;
    }
}

class Library {
    private List<Book> books;

    public Library() {
        books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added successfully!");
    }

    public void displayBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available in the library.");
        } else {
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }

    public void issueBook(String bookId) {
        for (Book book : books) {
            if (book.getBookId().equals(bookId)) {
                if (!book.isIssued()) {
                    book.issueBook();
                    System.out.println("Book issued successfully!");
                    return;
                } else {
                    System.out.println("Book is already issued.");
                    return;
                }
            }
        }
        System.out.println("Book ID not found.");
    }

    public void returnBook(String bookId) {
        for (Book book : books) {
            if (book.getBookId().equals(bookId)) {
                if (book.isIssued()) {
                    book.returnBook();
                    System.out.println("Book returned successfully!");
                    return;
                } else {
                    System.out.println("Book was not issued.");
                    return;
                }
            }
        }
        System.out.println("Book ID not found.");
    }
}
public class LibraryManagement {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nLibrary Management System:");
            System.out.println("1. Add Book");
            System.out.println("2. Display Books");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. Exit");

            int choice = -1;
            while (choice < 1 || choice > 5) {  // Loop until a valid choice is entered
                System.out.print("Enter your choice: ");
                try {
                    choice = scanner.nextInt();  // Read the menu choice
                    if (choice < 1 || choice > 5) {
                        System.out.println("Invalid choice. Please enter a number between 1 and 5.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid number between 1 and 5.");
                    scanner.nextLine();  // Clear the invalid input from the buffer
                }
            }

            scanner.nextLine();  // Clear the buffer before reading strings (for book details)
            
            switch (choice) {
                case 1:
                    System.out.print("Enter Book ID: ");
                    String bookId = scanner.nextLine();
                    System.out.print("Enter Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter Author: ");
                    String author = scanner.nextLine();
                    Book book = new Book(bookId, title, author);
                    library.addBook(book);
                    break;
                case 2:
                    library.displayBooks();
                    break;
                case 3:
                    System.out.print("Enter Book ID to issue: ");
                    String issueId = scanner.nextLine();
                    library.issueBook(issueId);
                    break;
                case 4:
                    System.out.print("Enter Book ID to return: ");
                    String returnId = scanner.nextLine();
                    library.returnBook(returnId);
                    break;
                case 5:
                    System.out.println("Exiting Library Management System. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

