package lt.verbus.controller;

import lt.verbus.exception.InsufficientFundsException;
import lt.verbus.exception.UserNotFoundException;
import lt.verbus.model.BankAccount;
import lt.verbus.model.CardType;
import lt.verbus.model.Transaction;
import lt.verbus.model.User;
import lt.verbus.repository.*;
import lt.verbus.services.BankAccountService;
import lt.verbus.services.BankService;
import lt.verbus.services.TransactionService;
import lt.verbus.services.UserService;
import lt.verbus.view.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Controller {
    private BankRepository bankRepository;
    private UserRepository userRepository;
    private BankAccountRepository bankAccountRepository;
    private TransactionRepository transactionRepository;

    private BankService bankService;
    private UserService userService;
    private BankAccountService bankAccountService;
    private TransactionService transactionService;

    private Scanner consoleInput;

    User currentUser;

    public Controller() throws IOException, SQLException {
        bankRepository = new BankRepository(ConnectionPool.getConnection(SqlDialect.MY_SQL));
        userRepository = new UserRepository(ConnectionPool.getConnection(SqlDialect.MY_SQL));
        bankAccountRepository = new BankAccountRepository(ConnectionPool.getConnection(SqlDialect.MY_SQL));
        transactionRepository = new TransactionRepository(ConnectionPool.getConnection(SqlDialect.MY_SQL));
        bankService = new BankService(bankRepository);
        userService = new UserService(userRepository);
        bankAccountService = new BankAccountService(bankAccountRepository);
        transactionService = new TransactionService(transactionRepository);
        consoleInput = new Scanner(System.in);
    }

    public void launchApp() throws SQLException {
        boolean stayInMenu = true;
        currentUser = null;
        while (stayInMenu) {
            MainMenu.display();
            switch (consoleInput.next()) {
                case "0":
                    System.exit(1);
                case "1":
                    switchToLoginMenu();
                    break;
                case "2":
                    ConsoleUi.displayRegisterMenu();
                    break;
                default:
                    ErrorMessenger.warnAboutFalseMenuEntry();
            }
        }
    }

    public void switchToLoginMenu() throws SQLException {
        boolean stayInMenu = true;
        while (stayInMenu) {
            LoginMenu.display();
            String userChoice = consoleInput.next();
            if (userChoice.equals("0")) {
                stayInMenu = false;
            } else {
                try {
                    currentUser = userService.findByUsername(userChoice);
                    switchToUserMenu();
                    stayInMenu = false;
                } catch (UserNotFoundException e) {
                    ErrorMessenger.warnAboutUserNotFound(userChoice);
                }
            }
        }
    }


    public void switchToUserMenu() throws SQLException {
        boolean stayInMenu = true;
        while (stayInMenu) {
            UserHomeMenu.display(currentUser);
            switch (consoleInput.next()) {
                case "0":
                    stayInMenu = false;
                    break;
                case "1":
                    ListPrinter.printNumeratedList(bankAccountRepository.findAllBelongingTo(currentUser));
                    break;
                case "2":
                    System.out.println("2 selected");
                    break;
                case "3":
                    switchToTopUpMenu();
                    break;
                case "4":
                    switchToWithdrawMenu();
                    break;
                case "5":
                    switchToTransferMoney();
                    break;
                default:
                    ErrorMessenger.warnAboutFalseMenuEntry();
            }
        }
    }

    public void switchToTopUpMenu() throws SQLException {
        UserTopUpMenu.display();
        Double amount = Double.parseDouble(consoleInput.next());
        //TODO: validate for numeric input
        BankAccount bankAccount = askToSelectAccount();
        //TODO: implement account top up operation
        System.out.println(bankAccount);
    }

    public void switchToWithdrawMenu() throws SQLException {
        UserWithdrawMenu.display();
        Double amount = Double.parseDouble(consoleInput.next());
        //TODO: validate for numeric input
        BankAccount bankAccount = askToSelectAccount();
        if (bankAccount.getBalance() < amount && bankAccount.getCardType().equals(CardType.DEBIT)) {
            ErrorMessenger.warnAboutInsufficientFunds();
        } else {
            //TODO: implement withdraw operation
        }
        System.out.println(bankAccount);
    }

    public void switchToTransferMoney() throws SQLException {
        UserTransferMenu.displayAmountRequest();
        Double amount = Double.parseDouble(consoleInput.next());
        //TODO: validate for numeric input
        BankAccount selectedSenderBankAccount = askToSelectAccount();
        User receivingUser = null;
        UserTransferMenu.displayReceiverRequest();
        String receiverUsernameEntered = consoleInput.next();
        try {
            System.out.println("Looking for receiver in database...");
            receivingUser = userService.findByUsername(receiverUsernameEntered);
            System.out.print("Receiver found:");
            System.out.println(receivingUser.getFullName());
        } catch (UserNotFoundException e) {
            ErrorMessenger.warnAboutUserNotFound(receiverUsernameEntered);
        }

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setSender(selectedSenderBankAccount);
        transaction.setReceiver(bankAccountService.findAllBelongingTo(receivingUser).get(0));
        try {
            transactionService.execute(transaction);
        } catch (InsufficientFundsException e) {
            ErrorMessenger.warnAboutInsufficientFunds();
        }
        InfoMessenger.informAboutSuccessfulTransfer();
    }


    private BankAccount askToSelectAccount() throws SQLException {
        List<BankAccount> bankAccounts = bankAccountRepository.findAllBelongingTo(currentUser);
        int chosenAccountNo = 1;
        boolean stayInMenu = true;
        while (stayInMenu) {
            UserChooseAccountMenu.display();
            ListPrinter.printNumeratedList(bankAccounts);
            chosenAccountNo = Integer.parseInt(consoleInput.next());
            //TODO: validate for numeric input
            if (chosenAccountNo < 1 || chosenAccountNo > bankAccounts.size()) {
                ErrorMessenger.warnAboutFalseMenuEntry();
            } else stayInMenu = false;
        }
        return bankAccounts.get(chosenAccountNo - 1);
    }
}
