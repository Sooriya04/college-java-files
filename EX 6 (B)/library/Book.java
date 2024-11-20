package library;
public class Book {
    private String title;
    private String author;
    private boolean isAvailable = true;
    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }
    public void displayInfo() {
        System.out.println("Title: " + title + ", Author: " + author + ", Available: " + (isAvailable ? "Yes" : "No"));
    }
    public String getTitle() {
        return title;
    }
    public boolean isAvailable() {
        return isAvailable;
    }
    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
