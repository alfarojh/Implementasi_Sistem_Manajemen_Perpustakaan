package library.models;

public class Book {
    // Penggunaan encapsulation untuk ISBN, title, writer, amount, dan status
    private final String ISBN;
    private final String title;
    private final String writer;
    private int amount;
    private String status;

    public Book(String ISBN, String title, String writer) {
        this.ISBN = ISBN;
        this.title = title;
        this.writer = writer;
        this.amount = 1;
        this.status = "active";
    }

    public String getISBN() {
        return ISBN;
    }

    public String getTitle() {
        return title;
    }

    public String getWriter() {
        return writer;
    }

    public int getAmount() {
        return amount;
    }

    public String getStatus() {
        return status;
    }

    public void deactivated() {
        this.status = "non active";
    }

    public void borrowBook() {
        amount = 0;
    }

    public void returnBook() {
        amount = 1;
    }

}
