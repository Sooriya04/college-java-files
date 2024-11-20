import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
class Library {
    private final List<String> books = new ArrayList<>();
    private final Lock lock = new ReentrantLock();
    public Library() {
        books.add("Berserk");
        books.add("Slam Dunk");
        books.add("Billy Bat");
        books.add("JJBA part 7");
        books.add("Vagabond");
        books.add("Tokyo Ghouls");
    }
    public void viewBooks() {
        lock.lock();
        try {
            System.out.println("\nAvailable Books:");
            if (books.isEmpty()) {
                System.out.println("No books available.");
            } else {
                for (int i = 0; i < books.size(); i++) {
                    System.out.println((i + 1) + ". " + books.get(i));
                }
            }
        } finally {
            lock.unlock();
        }
    }
    public void borrowBook(String bookName) {
        lock.lock();
        try {
            if (books.remove(bookName)) {
                System.out.println("\n" + bookName + " has been borrowed.");
            } else {
                System.out.println("\n" + bookName + " is not available.");
            }
        } finally {
            lock.unlock();
        }
    }
    public void returnBook(String bookName) {
        lock.lock();
        try {
            if (books.contains(bookName)) {
                System.out.println("\n" + bookName + " is already available in the library.");
            } else {
                books.add(bookName);
                System.out.println("\n" + bookName + " has been returned.");
            }
        } finally {
            lock.unlock();
        }
    }
    public void addBook(String bookName) {
        lock.lock();
        try {
            if (books.contains(bookName)) {
                System.out.println("\n" + bookName + " is already in the library.");
            } else {
                books.add(bookName);
                System.out.println("\n" + bookName + " has been added to the library.");
            }
        } finally {
            lock.unlock();
        }
    }
}
class LibraryTask implements Runnable {
    private final Library library;
    private final int action;
    private final String bookName;

    public LibraryTask(Library library, int action, String bookName) {
        this.library = library;
        this.action = action;
        this.bookName = bookName;
    }
    @Override
    public void run() {
        switch (action) {
            case 1 -> library.viewBooks();
            case 2 -> library.borrowBook(bookName);
            case 3 -> library.returnBook(bookName);
            case 4 -> library.addBook(bookName);
            default -> System.out.println("Invalid action.");
        }
    }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nLibrary Management System:");
            System.out.println("1. View Books");
            System.out.println("2. Borrow Book");
            System.out.println("3. Return Book");
            System.out.println("4. Add Book");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 
            if (choice == 5) {
                System.out.println("\nExiting...");
                break;
            }
            String bookName = null;
            if (choice == 2 || choice == 3 || choice == 4) {
                System.out.print("Enter book name: ");
                bookName = scanner.nextLine();
            }
            LibraryTask task = new LibraryTask(library, choice, bookName);
            Thread thread = new Thread(task);
            thread.start();

            try {
                thread.join(); 
            } catch (InterruptedException e) {
                System.out.println("Task interrupted.");
            }
        }
        scanner.close();
    }
}
