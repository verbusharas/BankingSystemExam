package lt.verbus.repository;

import lt.verbus.exception.UserNotFoundException;
import lt.verbus.model.Bank;
import lt.verbus.model.Transaction;
import lt.verbus.services.BankAccountService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class TransactionRepository {

    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private BankAccountService bankAccountService;

    public TransactionRepository(Connection connection) throws SQLException {
        this.connection = connection;
        this.statement = connection.createStatement();
    }

    public Transaction save(Transaction transaction) throws SQLException {
        String query = String.format("INSERT INTO transaction " +
                        "(sender_bank_account_id, receiver_bank_account_id, " +
                        "amount, timestamp) " +
                        "VALUES (%s, %d, %.2f, \"%s\")",
                transaction.getSender().getId(), transaction.getReceiver().getId(),
                transaction.getAmount(), transaction.getTimestamp().toString());
        statement.execute(query);
        // TODO: return object with sql generated id according to other repository examples
        return null;
    }
}
