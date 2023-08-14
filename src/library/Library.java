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

    public void inisiasi() {
        bookController.addBook("123-456-789-012-1", "dfsfsfds", "dfsfdfs");
        bookController.addBook("125-456-789-012-1", "dsfsdf", "asas");
    }

    //=================================== Book Section ======================================
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
        newLine();
    }

    public void removeBook() {
        System.out.println("Pilih hapus buku berdasarkan: ");
        System.out.println("1. ISBN");
        System.out.println("2. Nama / Author");
        System.out.println("0. Keluar");
        int choice = inputHandler.getIntegerInput("Masukkan pilihan: ");

        switch (choice) {
            case 0 -> System.out.println("Kembali ke menu utama.\n");
            case 1 -> {
                String ISBN = inputHandler.getInputText("Masukkan ISBN: ");
                if (convertISBN(ISBN).length() > 0) {
                    if (bookController.removeBook(convertISBN(ISBN))) {
                        System.out.println("Buku "
                                + bookController.getBookByIndex(bookController.getIndexBookByInput(convertISBN(ISBN))).getTitle()
                                + " berhasil dihapus.");
                    } else {
                        inputHandler.errorMessage("Buku "
                                + bookController.getBookByIndex(bookController.getIndexBookByInput(convertISBN(ISBN))).getTitle()
                                + " gagal dihapus.");
                    }
                } else {
                    inputHandler.errorMessage("ISBN tidak sesuai.");
                }
                newLine();
            }
            case 2 -> {
                String input = inputHandler.getInputText("Masukkan judul buku / author: ");
                if (bookController.removeBook(input)) {
                    System.out.println("Buku "
                            + bookController.getBookByIndex(bookController.getIndexBookByInput(input)).getTitle()
                            + " berhasil dihapus.");
                } else {
                    inputHandler.errorMessage("Buku "
                            + bookController.getBookByIndex(bookController.getIndexBookByInput(input)).getTitle()
                            + " gagal dihapus.");
                }
            }
            default -> System.out.println("Input diluat batas, kembali ke menu utama.");
        }
    }

    private String convertISBN(String ISBN) {
        String input = ISBN.replace("-", "");

        try {
            input = input.substring(0, 3) + "-" + input.substring(3, 6) + "-"
                    + input.substring(6, 9) + "-" + input.substring(9, 12) + "-"
                    + input.charAt(12);
            return input;
        } catch (Exception e) {
            return "";
        }
    }
    //=================================== Book Section ======================================

    //================================= Member Section ======================================

    public void addMember() {

    }


    //================================= Member Section ======================================

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
