package library;

import custom.InputHandler;
import library.controller.BookController;
import library.controller.MemberController;
import library.models.Book;
import library.models.LibraryRecord;
import library.models.Member;

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
        bookController.showBooks();
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
            inputHandler.errorMessage("ISBN tidak sesuai, kembali ke menu utama.");
        }
        newLine();
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
        String memberName = inputHandler.getInputText("Masukkan nama anggota baru: ");
        memberController.addMember(memberName);
        System.out.println("Penambahan member berhasil.");
        newLine();
    }

    public void removeMember() {
        String input = inputHandler.getInputText("Masukkan input member: ");

        if (memberController.removeMember(input)) {
            System.out.println("Member "
                    + memberController.getMemberByIndex(memberController.getIndexMemberByInput(input)).getName()
                    + " berhasil dihapus");
        } else {
            inputHandler.errorMessage("Member "
                    + memberController.getMemberByIndex(memberController.getIndexMemberByInput(input)).getName()
                    + " gagal dihapus");
        }
        newLine();
    }


    //================================= Member Section ======================================


    //================================= Library Section =====================================

    private void addRecord(Book book, Member member, boolean status) {
        libraryRecords.add(new LibraryRecord(book, member, status));
        System.out.println("Record berhasil ditambahkan.");
    }

    public void borrowBook() {
        memberController.showMembers();
        String inputMember = inputHandler.getInputText("Masukkan member yang ingin meminjam buku: ");

        if (!memberController.isMemberExistByInput(inputMember)) {
            inputHandler.errorMessage("Member tidak tersedia, kembali ke menu utama.");
            return;
        }

        String ISBN = inputHandler.getInputText("Masukkan ISBN: ");
        if (convertISBN(ISBN).length() == 0) {
            inputHandler.errorMessage("ISBN tidak sesuai, kembali ke menu utama.");
            return;
        } else {
            ISBN = convertISBN(ISBN);
        }

        addRecord(bookController.getBookByIndex(bookController.getIndexBookByInput(ISBN)),
                memberController.getMemberByIndex(memberController.getIndexMemberByInput(inputMember)),
                true);
        System.out.println("Buku "
                + bookController.getBookByIndex(bookController.getIndexBookByInput(ISBN)).getTitle()
                + " berhasil dipinjam oleh "
                + memberController.getMemberByIndex(memberController.getIndexMemberByInput(inputMember)).getName()
                + ".");
    }

    public void returnBorrowBook() {

    }

    //================================= Library Section =====================================

    // Fungsi untuk menampilkan baris baru sebanyak yang ditentukan.
    private void newLine(int... count) {
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
