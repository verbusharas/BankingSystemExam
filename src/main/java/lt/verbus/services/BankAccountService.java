package lt.verbus.services;


import lt.verbus.exception.InsufficientFundsException;
import lt.verbus.exception.UserNotFoundException;
import lt.verbus.model.BankAccount;
import lt.verbus.model.CardType;
import lt.verbus.model.Transaction;
import lt.verbus.repository.BankAccountRepository;

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

    public BankAccount findByIban(String iban) throws SQLException, UserNotFoundException {
        //TODO: validate if exists
        return bankAccountRepository.findByIban(iban);
    }

    public List<BankAccount> findAllBelongingTo(Object object) throws SQLException {
        //TODO: validate if exists?
        return bankAccountRepository.findAllBelongingTo(object);
    }

    public BankAccount findById(Long id) throws SQLException {
        //TODO: validate if exists
        return bankAccountRepository.findById(id);
    }

    public BankAccount save(BankAccount bankAccount) throws SQLException, UserNotFoundException {
        //TODO: validate if unique
        return bankAccountRepository.save(bankAccount);
    }

    public void update(BankAccount bankAccount) {
        //TODO: validate if exists
        bankAccountRepository.update(bankAccount);
    }

    public void delete(Long id) throws SQLException {
        //TODO: validate if exists
        bankAccountRepository.delete(id);
    }

    public String generateIban() {
        //TODO: make iban generator
        return null;
    }

    public boolean transferMoney(Transaction transaction) throws SQLException, InsufficientFundsException {
        double senderInitialBalance = transaction.getSender().getBalance();
        double amount = transaction.getAmount();
        if (senderInitialBalance < amount
                && transaction.getSender().getCardType().equals(CardType.DEBIT)) {
            throw new InsufficientFundsException();
        } else {
            System.out.println("[BankAccountService] Request for transfer received. Executing...");
            return bankAccountRepository.updateByTransaction(transaction);
        }
        //TODO: deal with credit situation
    }

}
