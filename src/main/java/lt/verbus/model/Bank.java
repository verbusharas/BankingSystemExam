package lt.verbus.model;

import java.util.List;

public class Bank {
    private long id;
    private String name;
    private List<BankAccount> bankAccounts;

    public Bank(long id, String name, List<BankAccount> bankAccounts) {
        this.id = id;
        this.name = name;
        this.bankAccounts = bankAccounts;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BankAccount> getBankAccounts() {
        return bankAccounts;
    }

    public void setBankAccounts(List<BankAccount> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }
}
