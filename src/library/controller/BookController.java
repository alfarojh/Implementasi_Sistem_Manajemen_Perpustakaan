package library.controller;

import library.models.Book;

import java.util.ArrayList;

public class BookController {
    private final ArrayList<Book> books;

    public BookController() {
        books = new ArrayList<>();
    }

    public boolean isBookExistByInput(String input) {
        for (Book book: books) {
            if (book.getISBN().equals(input)) {
                return true;
            } else if (book.getTitle().equalsIgnoreCase(input)) {
                return true;
            } else if (book.getWriter().equalsIgnoreCase(input)) {
                return true;
            }
        }
        return false;
    }

    public int getIndexBookByInput(String input) {
        for (int indexBook = 0; indexBook < books.size(); indexBook++) {
            if (books.get(indexBook).getISBN().equals(input)) {
                return indexBook;
            } else if (books.get(indexBook).getTitle().equalsIgnoreCase(input)) {
                return indexBook;
            } else if (books.get(indexBook).getWriter().equalsIgnoreCase(input)) {
                return indexBook;
            }
        }
        return -1;
    }

    public int getAmountAvailableByTitle(String title) {
        int sum = 0;
        for (Book book: books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                sum += book.getAmount();
            }
        }
        return sum;
    }

    public Book getBookByIndex(int index) {
        return books.get(index);
    }

    public ArrayList<Book> listBookByInput(String input) {
        ArrayList<Book> tempBook = new ArrayList<>();

        for (Book book: books) {
            if (book.getTitle().toLowerCase().contains(input.toLowerCase())) {
                tempBook.add(book);
            } else if (book.getWriter().toLowerCase().contains(input.toLowerCase())) {
                tempBook.add(book);
            }
        }
        return tempBook;
    }

    public boolean addBook(String ISBN, String title, String writer) {
        if (isBookExistByInput(ISBN)) return false;
        books.add(new Book(ISBN, title, writer));
        return true;
    }

    public boolean removeBook(String input) {
        if (isBookExistByInput(input)) {
            books.get(getIndexBookByInput(input)).deactivated();
            return true;
        }
        return false;
    }

    public void showBooks() {
        boolean bookIsNotExist = true;
        for (Book book: books) {
            if (book.getStatus().equalsIgnoreCase("active")) {
                System.out.println("ISBN: " + book.getISBN());
                System.out.println("Judul: " + book.getTitle());
                System.out.println("Penulis: " + book.getWriter());
                System.out.println("Jumlah: " + book.getAmount());
                System.out.println();
                bookIsNotExist = false;
            }
        }
        if (bookIsNotExist) {
            System.out.println("Buku tidak tersedia.");
        }
    }
}
