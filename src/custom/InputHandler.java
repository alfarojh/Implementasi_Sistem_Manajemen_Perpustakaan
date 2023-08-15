package custom;

import java.util.Scanner;

public class InputHandler {
    private final Scanner scanner = new Scanner(System.in);

    // Menampilkan pesan error dalam teks merah (untuk digunakan pada terminal yang mendukung ANSI escape code)
    public void errorMessage(String message) {
        System.out.println("\u001B[31m" + message + "\u001B[0m");
    }

    // Fungsi ini memeriksa apakah input merupakan bilangan bulat (digit) dan memiliki operator + atau -
    public int getIntegerInputWithOperator(String message) {
        System.out.print(message); // Menampilkan pesan kepada pengguna dan mendapatkan input
        String input = scanner.nextLine().trim();

        // Melakukan validasi untuk memastikan input hanya berisi digit dan merupakan bilangan positif atau negatif
        while (!input.matches("^[-+]?\\d+")) {
            // Jika input tidak valid, tampilkan pesan kesalahan dan minta input lagi
            errorMessage("Maaf, harap masukkan input berupa bilangan bulat atau menggunakan operator + atau -.");
            System.out.print(message);
            input = scanner.nextLine().trim();
        }
        // Mengubah input menjadi bilangan bulat dan mengembalikannya
        return Integer.parseInt(input);
    }

    // Fungsi ini memeriksa apakah input merupakan bilangan bulat (digit) dan memiliki operator + atau -
    public int getIntegerInput(String message) {
        System.out.print(message); // Menampilkan pesan kepada pengguna dan mendapatkan input
        String input = scanner.nextLine().trim();

        // Melakukan validasi untuk memastikan input hanya berisi digit dan merupakan bilangan positif atau negatif
        while (!input.matches("\\d+")) {
            // Jika input tidak valid, tampilkan pesan kesalahan dan minta input lagi
            errorMessage("Maaf, harap masukkan input berupa bilangan bulat dan positif.");
            System.out.print(message);
            input = scanner.nextLine().trim();
        }
        // Mengubah input menjadi bilangan bulat dan mengembalikannya
        return Integer.parseInt(input);
    }

    // Fungsi untuk menunda masukan dari pengguna
    public void delayInput () {
        scanner.nextLine();
    }

    // Fungsi untuk menerima masukan teks dari pengguna dengan menampilkan pesan terlebih dahulu.
    public String getInputText(String message) {
        System.out.print(message); // Menampilkan pesan kepada pengguna.
        String input = scanner.nextLine().trim(); // Menerima masukan dari pengguna dan menghapus spasi di awal dan akhir.
        while (input.equals("")) {
            errorMessage("Maaf, input tidak boleh kosong!"); // Menampilkan pesan kesalahan jika input kosong.
            System.out.print(message); // Menampilkan pesan kembali.
            input = scanner.nextLine().trim(); // Menerima masukan kembali dari pengguna.
        }
        return input; // Mengembalikan masukan yang sudah dihapus spasi.
    }

    // Fungsi untuk menampilkan baris baru sebanyak yang ditentukan.
    public void newLine(int... count) {
        // Jika parameter count diberikan, cetak baris baru sebanyak count[0] kali.
        if (count.length > 0) {
            for (int i = 0; i < count[0]; i++) {
                System.out.println();
            }
        } else {
            // Jika tidak ada parameter count, cetak satu baris baru.
            System.out.println();
        }
    }

    // Menutup scanner setelah penggunaan selesai
    public void close () {
        scanner.close();
    }
}
