package Medium.ATM;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AccountManager {
    private final Map<String,Account> accounts;
    public AccountManager(){
        accounts = new ConcurrentHashMap<>();
    }

    public void addAccount(Account account){
        accounts.put(account.getAccountNo(),account);
    }

    public void removeAccount(Account account){
        accounts.remove(account.getAccountNo());
    }

    public Account getAccountWithCardNo(String cardNo){
        return accounts.get(cardNo);
    }
}
