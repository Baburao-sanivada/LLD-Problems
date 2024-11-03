package Medium.LibraryManagementSystem;

import java.util.Arrays;
import java.util.List;

public class LibraryMSDemo {
    public static void run() {
        LibraryManagementSystem instance = LibraryManagementSystem.getInstance(10);
        Member member = instance.addMember("Babu","9390639493");
        Book book1 = instance.addBook("Ikigai","","Yang","2024");
        Book book2 = instance.addBook("Subtle Art","","Alex","2023");
        Book book3 = instance.addBook("Atomic Habits","","Yang","2024");
        Book book4 = instance.addBook("Think and grow rich","","Yang","2024");

        instance.getBooksAvailable();

        List<Book> books = Arrays.asList(book1,book2);
        Borrow borrow1 = instance.borrowBooks(books,member);
        System.out.println("Books available after borrow done");
        instance.getBooksAvailable();
        instance.returnBooks(borrow1.getBorrowId());
        System.out.println("Books available after return");
        instance.getBooksAvailable();
        System.out.println("Member Transaction");
        instance.showMemberTransaction(member.getMemberId());

    }
}
