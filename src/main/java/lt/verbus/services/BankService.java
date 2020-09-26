package lt.verbus.services;

import lt.verbus.exception.InstanceAlreadyExistsException;
import lt.verbus.model.Bank;
import lt.verbus.repository.BankRepository;

import java.sql.SQLException;
import java.util.List;

public class BankService {

    private BankRepository bankRepository;

    public BankService (BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    public List<Bank> findAll() throws SQLException {
        return bankRepository.findAll();
    }


}
