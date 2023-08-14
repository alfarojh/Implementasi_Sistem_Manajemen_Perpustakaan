package library;

import custom.InputHandler;
import library.controller.BookController;
import library.controller.MemberController;
import library.models.LibraryRecord;

import java.util.ArrayList;

public class Library {
    private final ArrayList<LibraryRecord> libraryRecords = new ArrayList<>();
    private final BookController bookController = new BookController();
    private final MemberController memberController = new MemberController();
    private final InputHandler inputHandler = new InputHandler();

    public void addBook() {
        String ISBN = inputHandler.getInputText("Masukkan ISBN: ").replace("-", "");
        boolean error = true;
        while (error) {
            error = false;

            if (!ISBN.matches("\\d+")) {
                inputHandler.errorMessage("Harus berupa bilangan bulat positif.");
                error = true;
            }
            if (ISBN.length() != 13) {
                inputHandler.errorMessage("ISBN harus mengandung 13 digit.");
                error = true;
            }
            if (!error) {
                if (bookController.isISBN_Exist(convertISBN(ISBN))) {
                    inputHandler.errorMessage("ISBN sudah ada, ketika 'keluar' untuk kembali ke menu utama");
                    error = true;
                } else {
                    break;
                }
            }

            newLine();
            ISBN = inputHandler.getInputText("Masukkan ISBN: ").replace("-", "");

            if (ISBN.equalsIgnoreCase("keluar")) break;
        }

        if (ISBN.equalsIgnoreCase("keluar")) {
            System.out.println("Kembali ke menu utama");
            return;
        }

        ISBN = convertISBN(ISBN);
        String title = inputHandler.getInputText("Masukkan judul buku: ");
        String author = inputHandler.getInputText("Masukkan nama pengarang: ");

        if (bookController.addBook(ISBN, title, author)) {
            System.out.println("Buku berhasil ditambahkan.");
        } else {
            inputHandler.errorMessage("Buku gagal ditambahkan.");
        }
    }

    private String convertISBN(String ISBN) {
        String input = ISBN.replace("-", "");

        input = input.substring(0, 3) + "-" + input.substring(3, 6) + "-"
                + input.substring(6, 9) + "-" + input.substring(9, 12) + "-"
                + input.charAt(12);
        return input;
    }

    // Fungsi untuk menampilkan baris baru sebanyak yang ditentukan.
    private static void newLine(int... count) {
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
}
