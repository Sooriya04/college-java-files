import java.util.ArrayList;
import java.util.Scanner;

// Interface for Borrower actions
interface Borrower {
    void borrowBook(ArrayList<Book> books, String bookTitle);
    void returnBook(ArrayList<Book> books, String bookTitle);
}

// Interface for Administrator actions
interface Administrator {
    void addBook(ArrayList<Book> books, String title, String author);
    void removeBook(ArrayList<Book> books, String title);
}

// Abstract base class for User
abstract class User {
    String name;
    String id;
    String password;

    public User(String name, String id, String password) {
        this.name = name;
        this.id = id;
        this.password = password;
    }

    public void viewBooks(ArrayList<Book> books) {
        System.out.println("Available books:");
        for (Book book : books) {
            book.displayInfo();
        }
    }

    abstract void performAction(ArrayList<Book> books, Scanner scanner);
}

// Concrete Member class implementing Borrower interface
class Member extends User implements Borrower {
    ArrayList<Book> borrowedBooks;

    public Member(String name, String id, String password) {
        super(name, id, password);
        this.borrowedBooks = new ArrayList<>();
    }

    @Override
    public void performAction(ArrayList<Book> books, Scanner scanner) {
        int action = scanner.nextInt();
        scanner.nextLine();  // Consume the newline

        if (action == 2) {
            System.out.println("Enter book title to borrow:");
            String borrowTitle = scanner.nextLine();
            borrowBook(books, borrowTitle);
        } else if (action == 3) {
            System.out.println("Enter book title to return:");
            String returnTitle = scanner.nextLine();
            returnBook(books, returnTitle);
        } else {
            System.out.println("Invalid action.");
        }
    }

    @Override
    public void borrowBook(ArrayList<Book> books, String bookTitle) {
        for (Book book : books) {
            if (book.title.equalsIgnoreCase(bookTitle) && book.isAvailable) {
                book.isAvailable = false;
                borrowedBooks.add(book);
                System.out.println(name + " borrowed: " + bookTitle);
                return;
            }
        }
        System.out.println("Book not available or not found.");
    }

    @Override
    public void returnBook(ArrayList<Book> books, String bookTitle) {
        for (Book book : borrowedBooks) {
            if (book.title.equalsIgnoreCase(bookTitle)) {
                book.isAvailable = true;
                borrowedBooks.remove(book);
                System.out.println(name + " returned: " + bookTitle);
                return;
            }
        }
        System.out.println("Book not found in your borrowed list.");
    }

    public void viewBorrowedBooks() {
        System.out.println(name + " has borrowed the following books:");
        for (Book book : borrowedBooks) {
            book.displayInfo();
        }
    }
}

// Concrete Librarian class implementing Administrator interface
class Librarian extends User implements Administrator {

    public Librarian(String name, String id, String password) {
        super(name, id, password);
    }

    @Override
    public void performAction(ArrayList<Book> books, Scanner scanner) {
        int action = scanner.nextInt();
        scanner.nextLine();  // Consume the newline

        if (action == 4) {
            System.out.println("Enter book title:");
            String addTitle = scanner.nextLine();
            System.out.println("Enter author:");
            String addAuthor = scanner.nextLine();
            addBook(books, addTitle, addAuthor);
        } else if (action == 5) {
            System.out.println("Enter book title to remove:");
            String removeTitle = scanner.nextLine();
            removeBook(books, removeTitle);
        } else {
            System.out.println("Invalid action.");
        }
    }

    @Override
    public void addBook(ArrayList<Book> books, String title, String author) {
        books.add(new Book(title, author));
        System.out.println("Book added: " + title + " by " + author);
    }

    @Override
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

// Book class
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

// Main class to run the library management system
public class LibraryManagement {

    public static User authenticate(User[] users, String id, String password) {
        for (User user : users) {
            if (user.id.equals(id) && user.password.equals(password)) {
                return user;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book("Harry Potter", "J.K. Rowling"));
        books.add(new Book("Lord of the Rings", "J.R.R. Tolkien"));

        Scanner scanner = new Scanner(System.in);

        // Create users
        User[] users = new User[] {
            new Librarian("Alice", "lib123", "libpass"),
            new Member("Bob", "mem456", "mempass")
        };

        boolean exit = false;
        while (!exit) {
            System.out.println("\n1. Login");
            System.out.println("2. Exit");
            System.out.print("Enter the choice here: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            switch (choice) {
                case 1:
                    System.out.println("Enter your ID:");
                    String id = scanner.nextLine();
                    System.out.println("Enter your password:");
                    String password = scanner.nextLine();

                    User currentUser = authenticate(users, id, password);
                    if (currentUser != null) {
                        currentUser.viewBooks(books);
                        boolean userSession = true;
                        while (userSession) {
                            if (currentUser instanceof Member) {
                                System.out.println("\n1. View Books");
                                System.out.println("2. Borrow Book");
                                System.out.println("3. Return Book");
                                System.out.println("4. Exit");
                                System.out.print("Enter The Choice Here: ");
                                int action = scanner.nextInt(); // Consume the newline

                                if (action == 1) {
                                    currentUser.viewBooks(books);
                                } else if (action == 2 || action == 3) {
                                    currentUser.performAction(books, scanner);
                                    if (action == 2) {
                                        ((Member) currentUser).viewBorrowedBooks();
                                    }
                                } else if (action == 4) {
                                    userSession = false;
                                } else {
                                    System.out.println("Invalid choice.");
                                }
                            } else if (currentUser instanceof Librarian) {
                                System.out.println("\n1. View Books");
                                System.out.println("2. Add Book");
                                System.out.println("3. Remove Book");
                                System.out.println("4. Exit");
                                System.out.print("Enter The Choice Here: ");
                                int action = scanner.nextInt();
                                scanner.nextLine(); // Consume the newline

                                if (action == 1) {
                                    currentUser.viewBooks(books);
                                } else if (action == 2 || action == 3) {
                                    currentUser.performAction(books, scanner);
                                } else if (action == 4) {
                                    userSession = false;
                                } else {
                                    System.out.println("Invalid choice.");
                                }
                            }
                        }
                    } else {
                        System.out.println("Invalid ID or password.");
                    }
                    break;
                case 2:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
        scanner.close();
    }
}
