import library.*;
import java.util.Scanner;
public class LibraryManagement {
    public static User authenticate(User[] users, String id, String password) {
        for (User user : users) {
            if (user.getId().equals(id) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }
    public static void main(String[] args) {
        Book[] books = new Book[10];
        books[0] = new Book("Harry Potter", "J.K. Rowling");
        books[1] = new Book("Lord of the Rings", "J.R.R. Tolkien");
        Scanner scanner = new Scanner(System.in);
        User[] users = {
            new Librarian("Librarian", "lib123", "libpass"),
            new Member("Sooriya", "sooriya007", "sooriya")
        };
        boolean exit = false;
        while (!exit) {
            System.out.println("\n1. Login");
            System.out.println("2. Exit");
            System.out.print("Enter the choice here: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.println("Enter your ID:");
                    String id = scanner.nextLine();
                    System.out.println("Enter your password:");
                    String password = scanner.nextLine();

                    User currentUser = authenticate(users, id, password);
                    if (currentUser != null) {
                        currentUser.viewBooks(books);
                        currentUser.performAction(books, scanner);
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
