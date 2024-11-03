package Medium.LibraryManagementSystem;

import java.util.List;
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

    public void addBorrowTransaction(Borrow borrow){
        borrowHistory.add(borrow);
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
