package library.models;

public class Member {
    // Menggunakan encapsulation pada id, name dan status
    private final String id;         // ID anggota
    private final String name;       // Nama anggota
    private String status;     // Status anggota (aktif/tidak aktif)

    // Konstruktor untuk inisialisasi objek anggota
    public Member(String id, String name) {
        this.id = id;
        this.name = name;
        this.status = "aktif";   // Status awal anggota adalah aktif
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

    // Menonaktifkan anggota dengan mengubah status menjadi "tidak aktif"
    public void deactivated() {
        this.status = "tidak aktif";
    }
}
