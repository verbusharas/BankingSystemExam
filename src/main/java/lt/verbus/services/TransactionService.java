package lt.verbus.services;

import lt.verbus.exception.InsufficientFundsException;
import lt.verbus.model.Transaction;
import lt.verbus.repository.BankAccountRepository;
import lt.verbus.repository.ConnectionPool;
import lt.verbus.repository.SqlDialect;
import lt.verbus.repository.TransactionRepository;

import java.io.IOException;
import java.sql.SQLException;

public class TransactionService {

    private BankAccountRepository bankAccountRepository;
    private BankAccountService bankAccountService;
    private TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) throws IOException, SQLException {
        bankAccountRepository = new BankAccountRepository(ConnectionPool.getConnection(SqlDialect.MY_SQL));
        bankAccountService = new BankAccountService(bankAccountRepository);
        this.transactionRepository = transactionRepository;
    }

    public void execute(Transaction transaction) throws InsufficientFundsException, SQLException {
        if (bankAccountService.transferMoney(transaction)) {
            System.out.println("[TransactionService] Received approval from database about executed transaction");
            transactionRepository.save(transaction);
        }
    }

}
