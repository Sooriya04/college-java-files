import java.util.*;
import java.util.logging.*;
class Book {
    String id;
    String title;
    boolean isBorrowed;
    public Book(String id, String title) {
        this.id = id;
        this.title = title;
        this.isBorrowed = false;
    }
}
class Library {
    private static final Logger logger = Logger.getLogger(Library.class.getName());
    private Map<String, Book> books = new HashMap<>();
    public Library() {
        books.put("101", new Book("101", "Berserk"));
        books.put("102", new Book("102", "JJBA Part 7"));
        books.put("103", new Book("103", "Vaga bond"));
        books.put("104", new Book("104", "Slam Dunk"));
        books.put("105", new Book("105", "Billy Bat"));

        try {
            FileHandler handler = new FileHandler("library.log", true);
            handler.setFormatter(new SimpleFormatter());
            logger.addHandler(handler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void addBook(String id, String title) {
        if (books.containsKey(id)) {
            System.out.println("Book with ID already exists!");
        } else {
            books.put(id, new Book(id, title));
            logger.info("Added Book: " + title);
            System.out.println("Book added successfully.");
        }
    }
    public void removeBook(String id) {
        if (books.containsKey(id)) {
            String title = books.get(id).title;
            books.remove(id);
            logger.info("Removed Book: " + title);
            System.out.println("Book removed successfully.");
        } else {
            System.out.println("Book with ID does not exist!");
        }
    }
    public void borrowBook(String id) {
        Book book = books.get(id);
        if (book == null) {
            System.out.println("Book not found!");
        } else if (book.isBorrowed) {
            System.out.println("Book is not available.");
        } else {
            book.isBorrowed = true;
            logger.info("Borrowed Book: " + book.title);
            System.out.println("Book borrowed successfully.");
        }
    }
    public void returnBook(String id) {
        Book book = books.get(id);
        if (book == null) {
            System.out.println("Book not found!");
        } else if (!book.isBorrowed) {
            System.out.println("Book was not borrowed.");
        } else {
            book.isBorrowed = false;
            logger.info("Returned Book: " + book.title);
            System.out.println("Book returned successfully.");
        }
    }
    public void viewBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in the library.");
        } else {
            System.out.println("Books in Library:");
            for (Book book : books.values()) {
                System.out.println("ID: " + book.id + ", Title: " + book.title + ", Status: " +
                        (book.isBorrowed ? "Borrowed" : "Available"));
            }
        }
    }
}
public class LibraryManagement {
    private static final Scanner scanner = new Scanner(System.in);
    private static Library library = new Library();

    public static void main(String[] args) {
        while (true) {
            System.out.println("Login as:\n1. Librarian\n2. Student\n3. Exit");
            System.out.print("Enter choice: ");
            int userType = scanner.nextInt();
            scanner.nextLine();

            switch (userType) {
                case 1:
                    librarianMenu();
                    break;
                case 2:
                    studentMenu();
                    break;
                case 3:
                    System.out.println("Exiting the system. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
    private static void librarianMenu() {
        System.out.print("Enter librarian password: ");
        String password = scanner.nextLine();

        if (!password.equals("admin123")) {
            System.out.println("Incorrect password!");
            return;
        }
        while (true) {
            System.out.println("\nLibrarian Menu:");
            System.out.println("1. Add Book\n2. Remove Book\n3. View Books\n4. Logout");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Enter book ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Enter book title: ");
                    String title = scanner.nextLine();
                    library.addBook(id, title);
                    break;
                case 2:
                    System.out.print("Enter book ID to remove: ");
                    String removeId = scanner.nextLine();
                    library.removeBook(removeId);
                    break;
                case 3:
                    library.viewBooks();
                    break;
                case 4:
                    System.out.println("Logged out.");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
    private static void studentMenu() {
        while (true) {
            System.out.println("\nStudent Menu:");
            System.out.println("1. Borrow Book\n2. Return Book\n3. View Books\n4. Logout");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter book ID to borrow: ");
                    String borrowId = scanner.nextLine();
                    library.borrowBook(borrowId);
                    break;
                case 2:
                    System.out.print("Enter book ID to return: ");
                    String returnId = scanner.nextLine();
                    library.returnBook(returnId);
                    break;
                case 3:
                    library.viewBooks();
                    break;
                case 4:
                    System.out.println("Logged out.");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
