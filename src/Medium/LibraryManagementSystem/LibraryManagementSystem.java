package Medium.LibraryManagementSystem;

import Medium.LinkedIn.User;
import Medium.LinkedIn.UserController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class LibraryManagementSystem {
    private static LibraryManagementSystem instance;
    private final BookController bookController;
    private final MemberController memberController;
    private final int maxDaysAllowed;
    private final Map<String,Member> transactionMap;
    private final Map<String,Borrow> borrowMap;
    AtomicLong bookId = new AtomicLong(0);
    AtomicLong memberId = new AtomicLong(0);
    AtomicLong borrowId = new AtomicLong(0);
    public synchronized static LibraryManagementSystem getInstance(int maxDaysAllowed){
        if(instance == null){
            instance = new LibraryManagementSystem(maxDaysAllowed);
        }
        return instance;
    }

    private LibraryManagementSystem( int maxDaysAllowed) {
        this.bookController = new BookController();
        this.memberController = new MemberController();
        this.maxDaysAllowed = maxDaysAllowed;
        borrowMap = new ConcurrentHashMap<>();
        transactionMap = new ConcurrentHashMap<>();
    }

    public Borrow borrowBooks(List<Book> books, Member member){
        bookController.markBooksAsBorrowed(books);
        borrowId.incrementAndGet();
        Borrow borrow = new Borrow(""+borrowId.get(),books, LocalDateTime.now(),LocalDateTime.now().plusDays(maxDaysAllowed));
        memberController.addBorrowTransaction(member.getMemberId(),borrow);
        borrowMap.put(borrow.getBorrowId(),borrow);
        transactionMap.put(borrow.getBorrowId(),member);
        return borrow;
    }

    public void getBooksAvailable(){
        bookController.showAvailableBooks();
    }

    public void showMemberTransaction(String memberId){
        memberController.showMemberTransaction(memberId);
    }

    public void returnBooks(String borrowId){
        Borrow borrow = borrowMap.get(borrowId);
        Member member = transactionMap.get(borrowId);
        bookController.markBooksAsAvailable(borrow.getBooks());
        memberController.returnTransaction(member.getMemberId(),borrow);
    }

    public Book addBook(String title,String ISBN, String author, String publishedYear){
        Book book = new Book(bookId.get()+"",ISBN,title,author,publishedYear);
        bookId.incrementAndGet();
        bookController.addBook(book);
        return book;
    }

    public void updateBook(Book book){
        bookController.updateBook(book);
    }

    public void deleteBook(Book book){
        bookController.deleteBook(book);
    }

    public Member addMember(String name,String contact){
        Member member = new Member(memberId.incrementAndGet()+"",name,contact);
        memberController.addMember(member);
        return member;
    }

}
