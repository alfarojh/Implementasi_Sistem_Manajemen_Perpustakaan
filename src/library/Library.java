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
        memberController.addMember("Udin");
        memberController.addMember("Santi");
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

            inputHandler.newLine();
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
        inputHandler.newLine();
    }

    public void removeBook() {
        String ISBN = inputHandler.getInputText("Masukkan ISBN: ");
        if (convertISBN(ISBN).length() > 0) {
            if (bookController.isISBN_Exist(convertISBN(ISBN))) {
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
                inputHandler.errorMessage("ISBN tidak ditemukan, kembali ke menu.");
                inputHandler.newLine();
            }
        } else {
            inputHandler.errorMessage("ISBN tidak sesuai, kembali ke menu.");
        }
        inputHandler.newLine();
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

    public void findBook() {
        System.out.println("Ketik '.' untuk menampilkan semua list buku.");
        String inputBook = inputHandler.getInputText("Masukkan judul / author: ");
        inputHandler.newLine();

        if (inputBook.equals(".")) {
            bookController.showBooks();
        } else {
            bookController.showBooksByInput(inputBook);
        }
        inputHandler.newLine();
    }
    //=================================== Book Section ======================================

    //================================= Member Section ======================================

    public void addMember() {
        String memberName = inputHandler.getInputText("Masukkan nama anggota baru: ");
        memberController.addMember(memberName);
        System.out.println("Penambahan member berhasil.");
        inputHandler.newLine();
    }

    public void removeMember() {
        String memberID = String.valueOf(inputHandler.getIntegerInput("Masukkan ID member yang ingin dihapus: "));

        if (memberController.isMemberExistByInput(memberID)) {
            if (memberController.removeMember(memberID)) {
                System.out.println("Member "
                        + memberController.getMemberByIndex(memberController.getIndexMemberByInput(memberID)).getName()
                        + " berhasil dihapus");
            } else {
                inputHandler.errorMessage("Member "
                        + memberController.getMemberByIndex(memberController.getIndexMemberByInput(memberID)).getName()
                        + " gagal dihapus");
            }
        } else {
            inputHandler.errorMessage("ID member tidak ditemukan");
        }
        inputHandler.newLine();
    }

    public void findMember() {
        System.out.println("Ketik '.' untuk menampilkan semua list member.");
        String inputMember = inputHandler.getInputText("Masukkan id / nama member: ");
        inputHandler.newLine();

        if (inputMember.equals(".")) {
            memberController.showMembers();
        } else{
            memberController.showMembersByInput(inputMember);
        }
        inputHandler.newLine();
    }
    //================================= Member Section ======================================


    //================================= Library Section =====================================

    private void addRecord(Book book, Member member, boolean status) {
        libraryRecords.add(new LibraryRecord(book, member, status));
        System.out.println("Record berhasil ditambahkan.");
    }

    public void borrowBook() {
        String memberID = String.valueOf(inputHandler.getIntegerInput("Masukkan ID member yang ingin meminjam buku: "));

        if (!memberController.isMemberExistByInput(memberID)) {
            inputHandler.errorMessage("Member tidak tersedia, kembali ke menu utama.");
            inputHandler.newLine();
            return;
        }

        String ISBN = inputHandler.getInputText("Masukkan ISBN: ");
        if (convertISBN(ISBN).length() == 0) {
            inputHandler.errorMessage("ISBN tidak sesuai, kembali ke menu utama.");
            inputHandler.newLine();
            return;
        } else {
            ISBN = convertISBN(ISBN);
            if (bookController.getBookByIndex(bookController.getIndexBookByInput(ISBN)).getAmount() == 0) {
                System.out.println("Buku sedang dipinjam, kembali ke menu.");
                inputHandler.newLine();
                return;
            }
        }

        bookController.getBookByIndex(bookController.getIndexBookByInput(ISBN)).borrowBook();
        addRecord(bookController.getBookByIndex(bookController.getIndexBookByInput(ISBN)),
                memberController.getMemberByIndex(memberController.getIndexMemberByInput(memberID)),
                true);
        System.out.println("Buku "
                + bookController.getBookByIndex(bookController.getIndexBookByInput(ISBN)).getTitle()
                + " berhasil dipinjam oleh "
                + memberController.getMemberByIndex(memberController.getIndexMemberByInput(memberID)).getName()
                + ".");
        inputHandler.newLine();
    }

    public void returnBorrowBook() {
        String ISBN = inputHandler.getInputText("Masukkan ISBN: ");
        if (convertISBN(ISBN).length() == 0) {
            inputHandler.errorMessage("ISBN tidak sesuai, kembali ke menu utama.");
            inputHandler.newLine();
            return;
        } else {
            ISBN = convertISBN(ISBN);
        }

        Member member = null;
        if (bookController.getBookByIndex(bookController.getIndexBookByInput(ISBN)).getAmount() == 1) {
            System.out.println("Buku masih ada di perpustakaan.");
            inputHandler.newLine();
            return;
        } else {
            for (int indexRecord = libraryRecords.size() - 1; indexRecord >= 0; indexRecord--) {
                if (libraryRecords.get(indexRecord).getBook().getISBN().equals(ISBN)) {
                    member = libraryRecords.get(indexRecord).getMember();
                    break;
                }
            }
        }

        if (member == null) {
            inputHandler.errorMessage("Gagal mengembalikan buku, kembali ke menu.");
            inputHandler.newLine();
            return;
        }

        bookController.getBookByIndex(bookController.getIndexBookByInput(ISBN)).returnBook();
        addRecord(bookController.getBookByIndex(bookController.getIndexBookByInput(ISBN)),
                member, false);
        System.out.println("Buku "
                + bookController.getBookByIndex(bookController.getIndexBookByInput(ISBN)).getTitle()
                + " berhasil dikembalikan oleh "
                + member.getName() + ".");
        inputHandler.newLine();
    }

    public void showRecord() {
        if (libraryRecords.size() == 0) {
            System.out.println("Belum ada catatan peminjaman. \n");
            inputHandler.newLine();
            return;
        }
        for (LibraryRecord libraryRecord: libraryRecords) {
            System.out.println("Waktu: " + libraryRecord.getTimestamp());
            System.out.println("Nama Member: " + libraryRecord.getMember().getName());
            System.out.println("Judul Buku: " + libraryRecord.getBook().getTitle());
            System.out.println("Status: " + libraryRecord.getStatusBorrow());
            inputHandler.newLine();
        }
    }

    //================================= Library Section =====================================
    
}
