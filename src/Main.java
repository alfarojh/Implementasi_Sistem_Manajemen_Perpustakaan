import library.controller.BookController;

public class Main {
    public static void main(String[] args) {
        BookController bookController = new BookController();
        bookController.addBook("d23wqedasd", "dasdasd", "dasdad");
        bookController.addBook("DASD", "dsasdad", "dasdad");
        bookController.addBook("DSADSAD", "dasdasd", "dasdad");
        bookController.getBookByIndex(0).borrowBook();
//        System.out.println(bookController.getAmountAvailableByBookTitle("dasdasd"));
        bookController.showBooks();
        bookController.getBookByIndex(0).returnBook();
        bookController.remove("dasdasd");
        bookController.showBooks();
    }

}