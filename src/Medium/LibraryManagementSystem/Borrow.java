package Medium.LibraryManagementSystem;

import java.time.LocalDateTime;
import java.util.List;

public class Borrow {
    private final String borrowId;
    private final List<Book> books;
    private final LocalDateTime time;
    private final LocalDateTime returnTime;

    public Borrow(String borrowId, List<Book> books, LocalDateTime time, LocalDateTime returnTime) {
        this.borrowId = borrowId;
        this.books = books;
        this.time = time;
        this.returnTime = returnTime;
    }

    public String getBorrowId() {
        return borrowId;
    }

    public List<Book> getBooks() {
        return books;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public LocalDateTime getReturnTime() {
        return returnTime;
    }
}
