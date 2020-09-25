package lt.verbus.model;

public class BankAccount {

    private long id;
    private Bank bank;
    private String iban;
    private CardType cardType;
    private User holder;

    public BankAccount(long id, Bank bank, String iban, CardType cardType, User holder) {
        this.id = id;
        this.bank = bank;
        this.iban = iban;
        this.cardType = cardType;
        this.holder = holder;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public User getHolder() {
        return holder;
    }

    public void setHolder(User holder) {
        this.holder = holder;
    }
}
