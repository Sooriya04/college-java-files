package Library;

public abstract class User {
    protected String id;
    protected String password;

    public User(String id, String password) {
        this.id = id;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public boolean authenticate(String id, String password) {
        return this.id.equals(id) && this.password.equals(password);
    }

    public abstract void viewBooks(Book[] books);

    public abstract void borrowBook(Book[] books, String title);

    public abstract void returnBook(Book[] books, String title);
}
