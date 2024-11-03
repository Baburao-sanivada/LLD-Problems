package Medium.LibraryManagementSystem;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class Member {
    private final String memberId;
    private final String name;
    private final String contact;
    private final List<Borrow> borrowHistory;

    public Member(String memberId, String name, String contact) {
        this.memberId = memberId;
        this.name = name;
        this.contact = contact;
        borrowHistory = new CopyOnWriteArrayList<>();
    }

    public void showTransactions(){
        System.out.println("---------------------------------------");
        System.out.println("History");
        for(Borrow borrow: borrowHistory){
            System.out.println("Id: "+borrow.getBorrowId());
            System.out.println("Books Taken");
            for(Book book: borrow.getBooks()){
                System.out.println(book.getTitle());
            }
            System.out.println("returnTime: "+borrow.getReturnTime());
            System.out.println("isReturned: "+borrow.getReturnStatus());
        }
        System.out.println("---------------------------------------");
    }

    public void addBorrowTransaction(Borrow borrow){
        borrowHistory.add(borrow);
    }

    public void returnTransaction(Borrow borrow){
        borrow.setAsReturned();
    }

    public String getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }
}
