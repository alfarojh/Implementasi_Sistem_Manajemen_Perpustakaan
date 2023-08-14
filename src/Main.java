import custom.InputHandler;
import library.Library;
import library.controller.BookController;
import library.controller.MemberController;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        library.addMember();
        library.removeMember();
    }

}