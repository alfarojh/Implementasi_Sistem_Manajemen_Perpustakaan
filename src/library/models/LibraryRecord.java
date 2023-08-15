package library.models;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class LibraryRecord {
    // Menggunakan encapsulation pada timestamp, book, member dan statusBorrow
    private final String timestamp;    // Waktu dan tanggal catatan
    private final Book book;           // Objek buku terkait dengan catatan
    private final Member member;       // Objek anggota terkait dengan catatan
    private String statusBorrow; // Status peminjaman (Dipinjam/Dikembalikan)

    // Konstruktor untuk membuat catatan peminjaman atau pengembalian buku
    public LibraryRecord(Book book, Member member, boolean statusBorrow) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        this.timestamp = format.format(timestamp);
        this.book = book;
        this.member = member;
        if (statusBorrow) {
            borrowBook();
        } else {
            returnBorrowedBook();
        }
    }

    // Mengembalikan waktu dan tanggal catatan
    public String getTimestamp() {
        return timestamp;
    }

    // Mengembalikan objek buku terkait dengan catatan
    public Book getBook() {
        return book;
    }

    // Mengembalikan objek anggota terkait dengan catatan
    public Member getMember() {
        return member;
    }

    // Mengembalikan status peminjaman (Dipinjam/Dikembalikan)
    public String getStatusBorrow() {
        return statusBorrow;
    }

    // Fungsi untuk menandai peminjaman buku dengan status "Dipinjam"
    public void borrowBook() {
        statusBorrow = "Dipinjam";
    }

    // Fungsi untuk menandai pengembalian buku dengan status "Dikembalikan"
    public void returnBorrowedBook() {
        statusBorrow = "Dikembalikan";
    }
}
