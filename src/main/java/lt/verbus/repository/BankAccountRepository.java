package lt.verbus.repository;

import lt.verbus.config.QueriesMySql;
import lt.verbus.model.Bank;
import lt.verbus.model.BankAccount;
import lt.verbus.model.CardType;
import lt.verbus.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BankAccountRepository extends GenericRepository<BankAccount> {

    private BankRepository bankRepository;
    private UserRepository userRepository;

    public BankAccountRepository(Connection connection) throws SQLException {
        super(connection, "bank_account");
    }

    public List<BankAccount> findAll() throws SQLException {
        return super.findAll();
    }

    public BankAccount findByIban(String iban) throws SQLException {
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
    public BankAccount save(BankAccount bankAccount) throws SQLException {
        preparedStatement = connection.prepareStatement(QueriesMySql.CREATE_BANK_ACCOUNT);
        preparedStatement.setLong(1, bankAccount.getBank().getId());
        preparedStatement.setString(2, bankAccount.getIban());
        preparedStatement.setString(3, bankAccount.getCardType().toString());
        preparedStatement.setLong(4, bankAccount.getHolder().getId());
        preparedStatement.setDouble(5, bankAccount.getBalance());
        preparedStatement.execute();
        return findByIban(bankAccount.getIban());
    }

    @Override
    public void update(BankAccount bankAccount) {
        //TODO implement update
    }

    public void delete(Long id) throws SQLException {
        super.delete(id);
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
