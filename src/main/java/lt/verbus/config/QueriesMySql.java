package lt.verbus.config;

import lt.verbus.repository.ConnectionPool;

import java.io.InputStream;
import java.util.Properties;

public class QueriesMySql {
    //GENERIC
    public static final String FIND_ALL_FROM = "SELECT * FROM ";
    public static final String FIND_BY_UNIQUE_IDENTIFIER = "SELECT * FROM ? WHERE ? = ?";
    public static final String FIND_BY_ID = "SELECT * FROM ? WHERE id = ?";
    public static final String DELETE_BY_ID = "DELETE FROM ? WHERE id = ?";
    public static final String FIND_ALL_BY_BANK_OR_USER = "SELECT * FROM bank_account WHERE ? = ?";

    //BANKS
    public static final String FIND_ALL_BANKS = "SELECT * FROM bank";
    public static final String FIND_BANK_BY_BIC = "SELECT * FROM bank WHERE bic = ?";
    public static final String FIND_BANK_BY_ID = "SELECT * FROM bank WHERE id = ?";

    public static final String CREATE_BANK = "INSERT INTO bank " +
            "(name, bic) VALUES (?, ?)";

    public static final String DELETE_BANK_BY_BIC = "DELETE FROM bank WHERE bic = ?";

    //USERS
    public static final String FIND_ALL_USERS = "SELECT * FROM user";
    public static final String FIND_USER_BY_USERNAME = "SELECT * FROM user WHERE username = ?";
    public static final String FIND_USER_BY_ID = "SELECT * FROM user WHERE id = ?";

    public static final String CREATE_USER = "INSERT INTO user " +
            "(username, full_name, phone_number) VALUES (?, ?, ?)";

    public static final String DELETE_USER_BY_USERNAME = "DELETE FROM user WHERE username = ?";

    //BANK ACCOUNTS

    public static final String FIND_ALL_BANK_ACCOUNTS = "SELECT * FROM bank_account";
    public static final String FIND_ALL_BANK_ACCOUNTS_BY_BANK_ID = "SELECT * FROM bank_account WHERE bank_id = ?";
    public static final String FIND_ALL_BANK_ACCOUNTS_BY_USER_ID = "SELECT * FROM bank_account WHERE user_id = ?";
    public static final String FIND_BANK_ACCOUNT_BY_IBAN = "SELECT * FROM bank_account WHERE iban = ?";
    public static final String FIND_BANK_ACCOUNT_BY_ID = "SELECT * FROM bank_account WHERE id = ?";

    public static final String CREATE_BANK_ACCOUNT = "INSERT INTO bank_account " +
            "(bank_id, iban, card_type, user_id, balance) VALUES (?,?,?,?,?)";

    public static final String DELETE_BANK_ACCOUNT_BY_IBAN = "DELETE FROM bank_accout WHERE iban = ?";


}

