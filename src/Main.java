import library.controller.BookController;
import library.controller.MemberController;

public class Main {
    public static void main(String[] args) {
//        BookController bookController = new BookController();
//        bookController.addBook("d23wqedasd", "dasdasd", "dasdad");
//        bookController.addBook("DASD", "dsasdad", "dasdad");
//        bookController.addBook("DSADSAD", "dasdasd", "dasdad");
//        bookController.getBookByIndex(0).borrowBook();
////        System.out.println(bookController.getAmountAvailableByBookTitle("dasdasd"));
//        bookController.showBooks();
//        bookController.getBookByIndex(0).returnBook();
//        bookController.removeBook("dasdasd");
//        bookController.showBooks();

        MemberController memberController = new MemberController();
//        memberController.addMember("sdasda");
//        memberController.addMember("dsa");
//        memberController.addMember("gfdg");
//        memberController.addMember("fgdf");
        memberController.showMembers();
        System.out.println();
        memberController.removeMember("4");
        memberController.removeMember("dsa");
        memberController.showMembers();
    }

}