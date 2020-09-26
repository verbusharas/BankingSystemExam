package lt.verbus.repository;

import lt.verbus.config.QueriesMySql;
import lt.verbus.model.User;

import java.sql.*;
import java.util.List;

public class UserRepository extends GenericRepository<User> {

    public UserRepository(Connection connection) throws SQLException {
        super(connection, "user");
    }

    public List<User> findAll() throws SQLException {
        return super.findAll();
    }

    public User findByUsername(String username) throws SQLException {
        return super.findByUniqueCode("username", username);
    }

    public User findById(long id) throws SQLException {
        return super.findById(id);
    }

    @Override
    public User save(User user) throws SQLException {
        preparedStatement = connection.prepareStatement(QueriesMySql.CREATE_USER);
        preparedStatement.setString(1, user.getUsername());
        preparedStatement.setString(2, user.getFullName());
        preparedStatement.setString(3, user.getPhoneNumber());
        preparedStatement.execute();
        return findByUsername(user.getUsername());
    }

    @Override
    public void update(User user) {
        //TODO implement update
    }

    public void delete(Long id) throws SQLException {
        super.delete(id);
    }

    @Override
    protected User convertTableToObject(ResultSet table) throws SQLException {
        User user = new User();
        user.setId(table.getInt("id"));
        user.setUsername(table.getString("username"));
        user.setFullName(table.getString("full_name"));
        user.setPhoneNumber(table.getString("phone_number"));
        return user;
    }
}
