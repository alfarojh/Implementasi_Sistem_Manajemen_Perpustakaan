package custom;

import java.util.Arrays;

public class TableGenerate {
    private final String title;
    private final String[] subTitle;
    private final char[] alignment;
    private final int[] textLength;
    private final int widthTabel;

    // Konstruktor untuk kelas Text yang digunakan untuk mencetak teks dalam bentuk tabel.
    public TableGenerate(String title, String[] subTitle, char[] alignment, int[] textLength) {
        // Inisialisasi atribut-atribut berdasarkan input yang diberikan.
        this.title = title;
        this.subTitle = subTitle;
        this.alignment = alignment;
        this.textLength = textLength;
        // Hitung lebar tabel berdasarkan panjang teks di setiap kolom dan judul subjudul.
        widthTabel = Arrays.stream(textLength).sum() + (subTitle.length * 3) - 1;
    }

    // Fungsi untuk mencetak judul tabel.
    public void printTitle() {
        line();  // Cetak garis batas atas tabel.
        // Cetak judul tabel dengan rata kanan.
        System.out.println("|  " + title + " ".repeat(widthTabel - title.length() - 4) + "  |");
        printSubTitle();  // Cetak subjudul tabel.
    }

    // Fungsi untuk mencetak subjudul tabel.
    public void printSubTitle() {
        line();  // Cetak garis batas atas sub judul.
        System.out.print("| ");
        for (int indexSubTitle = 0; indexSubTitle < textLength.length; indexSubTitle++) {
            int length = (textLength[indexSubTitle] - subTitle[indexSubTitle].length()) / 2;
            System.out.print(" ".repeat(length) + subTitle[indexSubTitle]);
            if ((textLength[indexSubTitle] - subTitle[indexSubTitle].length()) % 2 == 0) {
                System.out.print(" ".repeat(length));
            } else {
                System.out.print(" ".repeat(length + 1));
            }
            System.out.print(" | ");
        }
        System.out.println();
        line();  // Cetak garis batas bawah subjudul tabel.
    }

    // Fungsi untuk mencetak isi tabel.
    public void printBody(int index, String[] content) {
        // Berikan warna biru pada baris genap untuk membedakan isi tabel.
        if (index % 2 == 0) {
            System.out.print("\033[34m"); // Warna biru (kode ANSI).
        }
        System.out.print("| ");
        for (int indexSubTitle = 0; indexSubTitle < content.length; indexSubTitle++) {
            if (alignment[indexSubTitle] == 'c' || alignment[indexSubTitle] == 'C') {
                // Cetak konten rata tengah.
                int length = (textLength[indexSubTitle] - content[indexSubTitle].length()) / 2;
                System.out.print(" ".repeat(length) + content[indexSubTitle]);
                if ((textLength[indexSubTitle] - content[indexSubTitle].length()) % 2 == 0) {
                    System.out.print(" ".repeat(length));
                } else {
                    System.out.print(" ".repeat(length + 1));
                }
                System.out.print(" | ");
            } else {
                // Cetak konten tanpa perataan (alignment) atau perataan ke kiri.
                System.out.print(content[indexSubTitle] + " ".repeat(textLength[indexSubTitle] - content[indexSubTitle].length()) + " | ");
            }
        }
        System.out.println("\u001B[0m");  // Kembalikan warna ke default (kode ANSI).
    }

    // Fungsi untuk mencetak pesan dalam bentuk tabel.
    public void printMessage(String message) {
        System.out.print("| ");

        // Hitung panjang kosong yang diperlukan untuk perataan tengah pesan dalam tabel.
        int length = widthTabel - message.length() - 2;

        // Cetak spasi pada bagian kiri pesan agar pesan terletak di tengah sel tabel.
        System.out.print(" ".repeat(length / 2) + message);

        // Cek apakah panjang kosong genap atau ganjil, lalu cetak spasi yang sesuai di bagian kanan pesan.
        if (length % 2 == 0) {
            System.out.print(" ".repeat(length / 2));
        } else {
            System.out.print(" ".repeat(length / 2 + 1));
        }

        // Cetak garis batas kanan sel tabel dan pindah baris.
        System.out.print(" | \n");
    }

    // Fungsi untuk mencetak pesan tombol "Keluar" dalam tabel.
    // Parameter "index" digunakan untuk menentukan apakah baris tombol berada pada indeks genap atau ganjil.
    public void messageExit(int index) {
        if (index % 2 == 0) {
            System.out.print("\033[34m"); // Mengatur warna teks ke biru (kode ANSI).
            System.out.print("|" + " ".repeat(textLength[0]/2)
                    + "0" + " ".repeat(textLength[0]/2) + "| ");
        } else {
            System.out.print("|" + " ".repeat(textLength[0]/2)
                    + "0" + " ".repeat(textLength[0]/2+ 1) + "| ");
        }

        // Cetak teks "keluar" di dalam sel tombol, dengan spasi agar berada di tengah sel.
        System.out.print("keluar" + " ".repeat(widthTabel - textLength[0] - 10) + "|");
        System.out.println("\u001B[0m");  // Mengembalikan warna teks ke default (kode ANSI).
    }

    // Fungsi untuk mencetak garis pembatas tabel.
    public void line() {
        System.out.println("|" + "=".repeat(widthTabel) + "|");
    }

}
