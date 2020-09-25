package lt.verbus.model;

import java.util.List;

public class User {

    private long id;
    private String name;
    private String surname;
    private List<BankAccount> bankAccounts;

    public User(long id, String name, String surname, List<BankAccount> bankAccounts) {
        this.id = id;
        this.name = name;
        this.surname = surname;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List<BankAccount> getBankAccounts() {
        return bankAccounts;
    }

    public void setBankAccounts(List<BankAccount> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }
}
