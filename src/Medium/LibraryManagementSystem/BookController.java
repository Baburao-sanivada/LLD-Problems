package Medium.LibraryManagementSystem;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BookController {
    private final Map<String,Book> books;

    public BookController() {
        this.books = new ConcurrentHashMap<>();
    }

    public void addBook(Book book){
        books.put(book.getBookId(),book);
    }

    public void deleteBook(Book book){
        books.remove(book.getBookId());
    }

    public void updateBook(Book book){
        books.put(book.getBookId(), book);
    }

    public void markBooksAsBorrowed(List<Book> borrowedBooks){
        for(Book book:borrowedBooks){
            book.setBookStatus(AvailabilityStatus.BORROWED);
        }
    }

    public void markBooksAsAvailable(List<Book> returnedBooks){
        for(Book book:returnedBooks){
            book.setBookStatus(AvailabilityStatus.AVAILABLE);
        }
    }
}
