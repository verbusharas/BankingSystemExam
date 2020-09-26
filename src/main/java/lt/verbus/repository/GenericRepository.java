package lt.verbus.repository;

import lt.verbus.config.QueriesMySql;
import lt.verbus.model.Bank;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class GenericRepository<T> {
    protected final Connection connection;
    protected final Statement statement;
    protected PreparedStatement preparedStatement;
    private final String databaseTableName;

    protected GenericRepository(Connection connection, String databaseTableName) throws SQLException {
        this.databaseTableName = databaseTableName;
        this.connection = connection;
        this.statement = connection.createStatement();
    }

    protected List<T> findAll() throws SQLException {
        String query = String.format("SELECT * FROM %s", databaseTableName);
        ResultSet table = statement.executeQuery(query);
        return convertTableToList(table);
    }

    protected T findByUniqueCode(String codeColumnName, String code) throws SQLException {
        String query = String.format("SELECT * FROM %s WHERE %s = \"%s\"", databaseTableName, codeColumnName, code);
        ResultSet table = statement.executeQuery(query);
        table.next();
        return convertTableToObject(table);
    }

    protected T findById(long id) throws SQLException {
        String query = String.format("SELECT * FROM %s WHERE id = %d", databaseTableName, id);
        ResultSet table = statement.executeQuery(query);
        table.next();
        return convertTableToObject(table);
    }

    abstract T save(T t) throws SQLException;

    abstract void update(T t) throws SQLException;

    protected void delete(Long id) throws SQLException {
        String query = String.format("DELETE FROM %s WHERE id = %d", databaseTableName, id);
        statement.executeUpdate(query);
    }

    protected List<T> convertTableToList(ResultSet table) throws SQLException {
        List<T> list = new ArrayList<>();
        while (table.next()) {
            list.add(convertTableToObject(table));
        }
        return list;
    }

    abstract T convertTableToObject(ResultSet table) throws SQLException;

}
