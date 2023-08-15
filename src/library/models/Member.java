package library.models;

public class Member {
    // Menggunakan encapsulation pada id, name dan status
    private final String id;         // ID anggota
    private final String name;       // Nama anggota
    private String status;     // Status anggota (aktif/tidak aktif)
    private int borrowedBookCount;

    // Konstruktor untuk inisialisasi objek anggota
    public Member(String id, String name) {
        this.id = id;
        this.name = name;
        this.status = "aktif";   // Status awal anggota adalah aktif
        this.borrowedBookCount = 0;
    }

    // Mengembalikan ID anggota
    public String getId() {
        return id;
    }

    // Mengembalikan nama anggota
    public String getName() {
        return name;
    }

    // Mengembalikan status anggota (aktif/tidak aktif)
    public String getStatus() {
        return status;
    }

    public int getBorrowedBookCount() {
        return borrowedBookCount;
    }

    // Menonaktifkan anggota dengan mengubah status menjadi "tidak aktif"
    public void deactivated() {
        this.status = "tidak aktif";
    }

    public void borrowBook() {
        borrowedBookCount += 1;
    }

    public void returnBook() {
        borrowedBookCount -= 1;
    }
}
