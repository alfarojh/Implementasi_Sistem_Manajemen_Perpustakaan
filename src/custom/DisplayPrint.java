package custom;

public class DisplayPrint {

    // Fungsi untuk menghasilkan tampilan menu utama.
    public String displayMenu() {
        return """
                |=======================================|
                |                  Menu                 |
                |=======================================|
                |  1.  Kelola Kategori                  |
                |  2.  Kelola Barang                    |
                |  3.  Transaksi                        |
                |  0.  Keluar                           |
                |=======================================|
                Silahkan masukkan pilihan:\s""";
    }

    // Fungsi untuk menghasilkan tampilan menu kelola kategori.
    public String displayMenuCategory() {
        return """
                |=======================================|
                |               Kategori                |
                |=======================================|
                |  1.  Tambah                           |
                |  2.  Hapus                            |
                |  3.  Update Nama                      |
                |  4.  Tampilkan                        |
                |  0.  Keluar                           |
                |=======================================|
                Silahkan masukkan pilihan:\s""";
    }

    // Fungsi untuk menghasilkan tampilan menu kelola barang.
    public String displayMenuItem() {
        return """
                |=======================================|
                |                Barang                 |
                |=======================================|
                |  1.  Tambah                           |
                |  2.  Hapus                            |
                |  3.  Update Nama                      |
                |  4.  Cari                             |
                |  5.  Transaksi                        |
                |  6.  Tampilkan                        |
                |  0.  Keluar                           |
                |=======================================|
                Silahkan masukkan pilihan:\s""";
    }

    // Fungsi untuk menghasilkan tampilan menu transaksi.
    public String displayTransaction() {
        return """
                |=======================================|
                |                Transaksi              |
                |=======================================|
                |  1.  Transaksi Barang                 |
                |  2.  Masukkan Barang ke Kategori      |
                |  3.  Tampilkan Relasi Barang Kategori |
                |  4.  Tampilkan Transaksi Barang       |
                |  0.  Keluar                           |
                |=======================================|
                Silahkan masukkan pilihan:\s""";
    }
}
