package library.models;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class LibraryRecord {
    // Penggunaan encapsulation untuk timestamp, book, member dan statusBorrow
    private final String timestamp;
    private final Book book;
    private final Member member;
    private String statusBorrow;

    public LibraryRecord(Book book, Member member, boolean statusBorrow) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        this.timestamp = format.format(timestamp);;
        this.book = book;
        this.member = member;
        if (statusBorrow) {
            borrowBook();
        } else {
            returnBorrowedBook();
        }
    }

    public String getTimestamp() {
        return timestamp;
    }

    public Book getBook() {
        return book;
    }

    public Member getMember() {
        return member;
    }

    public String getStatusBorrow() {
        return statusBorrow;
    }

    public void borrowBook() {
        statusBorrow = "Dipinjam";
    }

    public void returnBorrowedBook() {
        statusBorrow = "Dikembalikan";
    }
}
