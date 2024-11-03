package Medium.LibraryManagementSystem;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MemberController {
    private final Map<String,Member> members;

    public MemberController() {
        this.members = new ConcurrentHashMap<>();
    }

    public void addMember(Member member){
        members.put(member.getMemberId(),member);
    }

    public void addBorrowTransaction(String memberId,Borrow borrow){
        Member member = members.get(memberId);
        member.addBorrowTransaction(borrow);
    }
}
