package lt.verbus.config;

public class QueriesMySql {
    //GENERIC
    public static final String FIND_ALL_FROM = "SELECT * FROM ";

    //BANKS
    public static final String CREATE_BANK = "INSERT INTO bank " +
            "(name, bic) VALUES (?, ?)";

    //USERS
    public static final String CREATE_USER = "INSERT INTO user (username, full_name, phone_number) VALUES (?, ?, ?)";

    //BANK ACCOUNTS
    public static final String CREATE_BANK_ACCOUNT = "INSERT INTO bank_account " +
            "(bank_id, iban, card_type, user_id, balance) VALUES (?,?,?,?,?)";
}

