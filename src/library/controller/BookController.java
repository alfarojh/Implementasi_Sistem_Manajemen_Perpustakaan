package library.controller;

import custom.TableGenerate;
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
            } else if (book.getAuthor().equalsIgnoreCase(input)) {
                return true;
            }
        }
        return false;
    }

    public boolean isISBN_Exist(String ISBN) {
        for (Book book: books) {
            if (book.getISBN().equals(ISBN)) {
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
            } else if (books.get(indexBook).getAuthor().equalsIgnoreCase(input)) {
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

    public boolean addBook(String ISBN, String title, String author) {
        if (isBookExistByInput(ISBN)) return false;
        books.add(new Book(ISBN, title, author));
        return true;
    }

    public boolean removeBook(String input) {
        if (isBookExistByInput(input)) {
            books.get(getIndexBookByInput(input)).deactivated();
            return true;
        }
        return false;
    }

    public void showAllBooks() {
        showBooksByInput("");
    }

    public void showBooksByInput (String input) {
        boolean bookIsNotExist = true;
        TableGenerate tableGenerate = new TableGenerate("",
                new String[]{"ISBN", "Judul", "Penulis", "Jumlah"},
                new char[]{'c','l','l','c'},
                new int[]{17, 70, 40, 6});
        tableGenerate.printSubTitle();
        for (int indexBook = 0; indexBook < books.size(); indexBook++) {
            if (books.get(indexBook).getStatus().equalsIgnoreCase("active")) {
                if (books.get(indexBook).getTitle().toLowerCase().contains(input.toLowerCase()) ||
                        books.get(indexBook).getAuthor().toLowerCase().contains(input.toLowerCase())) {
                    tableGenerate.printBody(indexBook, new String[]{
                            books.get(indexBook).getISBN(),
                            books.get(indexBook).getTitle(),
                            books.get(indexBook).getAuthor(),
                            String.valueOf(books.get(indexBook).getAmount())
                    });
                    bookIsNotExist = false;
                }
            }
        }
        if (bookIsNotExist) {
            tableGenerate.printMessage("Buku tidak tersedia.");
        }
        tableGenerate.line();
    }
}
