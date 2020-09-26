package lt.verbus.services;


import lt.verbus.config.QueriesMySql;
import lt.verbus.model.Bank;
import lt.verbus.model.BankAccount;
import lt.verbus.model.User;
import lt.verbus.repository.BankAccountRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BankAccountService {

    private final BankAccountRepository bankAccountRepository;

    public BankAccountService(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    public List<BankAccount> findAll() throws SQLException {
        return bankAccountRepository.findAll();
    }

    public BankAccount findByIban(String iban) throws SQLException {
        return bankAccountRepository.findByIban(iban);
    }

    public List<BankAccount> findAllBelongingTo(Object object) throws SQLException {
        return bankAccountRepository.findAllBelongingTo(object);
    }

    public BankAccount findById(Long id) throws SQLException {
        return bankAccountRepository.findById(id);
    }

    public BankAccount save(BankAccount bankAccount) throws SQLException {
        return bankAccountRepository.save(bankAccount);
    }

    public void update(BankAccount bankAccount) {
        bankAccountRepository.update(bankAccount);
    }

    public void delete(Long id) throws SQLException {
        bankAccountRepository.delete(id);
    }


}
