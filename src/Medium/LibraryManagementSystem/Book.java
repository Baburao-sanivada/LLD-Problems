package Medium.LibraryManagementSystem;

public class Book {
    private final String bookId;
    private final String ISBN;
    private final String title;
    private final String author;
    private final String publishedYear;
    private AvailabilityStatus bookStatus;

    public Book(String bookId, String ISBN, String title, String author, String publishedYear) {
        this.bookId = bookId;
        this.ISBN = ISBN;
        this.title = title;
        this.author = author;
        this.publishedYear = publishedYear;
    }

    public String getBookId() {
        return bookId;
    }

    public String getISBN() {
        return ISBN;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublishedYear() {
        return publishedYear;
    }

    public AvailabilityStatus getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(AvailabilityStatus bookStatus) {
        this.bookStatus = bookStatus;
    }
}
