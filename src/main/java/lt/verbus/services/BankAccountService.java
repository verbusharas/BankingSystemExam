package lt.verbus.services;

import lt.verbus.config.QueriesMySql;
import lt.verbus.model.Bank;
import lt.verbus.model.BankAccount;
import lt.verbus.repository.BankAccountRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BankAccountService {

    private BankAccountRepository bankAccountRepository;

    public BankAccountService(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }



}
