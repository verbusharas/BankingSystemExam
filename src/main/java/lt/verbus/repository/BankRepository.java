package lt.verbus.repository;

import lt.verbus.config.QueriesMySql;
import lt.verbus.model.Bank;

import java.sql.*;
import java.util.List;

public class BankRepository extends GenericRepository<Bank> {

    public BankRepository(Connection connection) throws SQLException {
        super(connection, "bank");
    }

    public List<Bank> findAll() throws SQLException {
        return super.findAll();
    }

    public Bank findByBic(String bic) throws SQLException {
        return super.findByUniqueCode("bic", bic);
    }

    public Bank findById(long id) throws SQLException {
        return super.findById(id);
    }

    @Override
    public Bank save(Bank bank) throws SQLException {
        preparedStatement = connection.prepareStatement(QueriesMySql
                .CREATE_BANK);
        preparedStatement.setString(1, bank.getName());
        preparedStatement.setString(2, bank.getBic());
        preparedStatement.execute();
        return findByBic(bank.getBic());
    }

    @Override
    public void update(Bank bank) {
        //TODO implement update
    }

    public void delete(Long id) throws SQLException {
        super.delete(id);
    }

    @Override
    protected Bank convertTableToObject(ResultSet table) throws SQLException {
        Bank bank = new Bank();
        bank.setId(table.getInt("id"));
        bank.setBic(table.getString("bic"));
        bank.setName(table.getString("name"));
        return bank;
    }
}
