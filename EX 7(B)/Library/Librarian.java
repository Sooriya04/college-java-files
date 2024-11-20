package Library;

public class Librarian extends User {

    public Librarian(String id, String password) {
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
        System.out.println("Librarians cannot borrow books.");
    }

    @Override
    public void returnBook(Book[] books, String title) {
        System.out.println("Librarians cannot return books.");
    }
}
