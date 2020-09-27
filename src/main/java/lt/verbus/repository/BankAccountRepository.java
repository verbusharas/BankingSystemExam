package lt.verbus.repository;

import lt.verbus.config.QueriesMySql;
import lt.verbus.exception.UserNotFoundException;
import lt.verbus.model.*;

import java.sql.*;
import java.util.List;

public class BankAccountRepository extends GenericRepository<BankAccount> {

    public BankAccountRepository(Connection connection) throws SQLException {
        super(connection, "bank_account");
    }

    public List<BankAccount> findAll() throws SQLException {
        return super.findAll();
    }

    public BankAccount findByIban(String iban) throws SQLException, UserNotFoundException {
        return super.findByUniqueCode("iban", iban);
    }

    public List<BankAccount> findAllBelongingTo(Object object) throws SQLException {
        String query = (QueriesMySql.FIND_ALL_FROM + "bank_account WHERE ");
        if (object instanceof User) {
            query += "user_id = " + ((User) object).getId();
        } else if (object instanceof Bank) {
            query += "bank_id = " + ((Bank) object).getId();
        } else return null;
        ResultSet table = statement.executeQuery(query);
        return convertTableToList(table);
    }

    public BankAccount findById(Long id) throws SQLException {
        return super.findById(id);
    }

    @Override
    public BankAccount save(BankAccount bankAccount) throws SQLException, UserNotFoundException {
        String query = String.format("INSERT INTO bank_account " +
                        "(bank_id, iban, card_type, user_id, balance) " +
                        "VALUES (%s, %s, %s, %s, %s)",
                bankAccount.getBank().getId(),
                bankAccount.getIban(),
                bankAccount.getCardType().toString(),
                bankAccount.getHolder().getId(),
                bankAccount.getBalance());
        statement.executeQuery(query);
        return findByIban(bankAccount.getIban());
    }

    @Override
    public void update(BankAccount bankAccount) {
        //TODO implement update
    }

    public void delete(Long id) throws SQLException {
        super.delete(id);
    }

    public boolean updateByTransaction(Transaction transaction) throws SQLException {
        BankAccount sender = transaction.getSender();
        BankAccount receiver = transaction.getReceiver();
        Double amount = transaction.getAmount();
        connection.setAutoCommit(false);
        try {
            String queryForSender = String.format("UPDATE bank_account " +
                            "SET balance = balance - %.2f WHERE id = %d",
                    amount, sender.getId()
            );
            String queryForReceiver = String.format("UPDATE bank_account " +
                            "SET balance = balance + %.2f WHERE id = %d",
                    amount, receiver.getId()
            );
            statement.executeUpdate(queryForSender);
            statement.executeUpdate(queryForReceiver);
            connection.commit();
        } catch (Exception e) {
            System.out.println("[BankAccountRepository] Transfer unsuccesful. Rolling back changes.");
            e.printStackTrace();
            connection.rollback();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }
        System.out.println("[BankAccountRepository] Transfer Succesful.");
        return true;
    }

    protected BankAccount convertTableToObject(ResultSet table) throws SQLException {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setId(table.getInt("id"));
        bankAccount.setIban(table.getString("iban"));
        bankAccount.setBalance(table.getDouble("balance"));
        bankAccount.setCardType(CardType
                .valueOf(table.getString("card_type").toUpperCase()));

        BankRepository bankRepository = new BankRepository(connection);
        bankAccount.setBank(bankRepository.findById(table.getInt("bank_id")));

        UserRepository userRepository = new UserRepository(connection);
        bankAccount.setHolder(userRepository.findById(table.getInt("user_id")));

        return bankAccount;
    }


}
