package lt.verbus;

import lt.verbus.model.Bank;
import lt.verbus.model.BankAccount;
import lt.verbus.model.CardType;
import lt.verbus.model.User;
import lt.verbus.repository.*;
import lt.verbus.services.PrinterService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Random;

public class App {
    public static void main(String[] args) throws IOException, SQLException {

        PrinterService printer = new PrinterService();
        BankRepository bankRepository = new BankRepository(ConnectionPool.getConnection(SqlDialect.MY_SQL));
        UserRepository userRepository = new UserRepository(ConnectionPool.getConnection(SqlDialect.MY_SQL));
        BankAccountRepository bankAccountRepository = new BankAccountRepository(ConnectionPool.getConnection(SqlDialect.MY_SQL));

        printer.printListToConsole(bankAccountRepository.findAll());

        User merkel = userRepository.findById(2);

        System.out.println();
        printer.printListToConsole(bankAccountRepository.findAllBelongingTo(merkel));

        System.out.println();
        printer.printListToConsole(bankRepository.findAll());

        System.out.println();
        printer.printListToConsole(userRepository.findAll());

        ConnectionPool.closeConnections();
    }


}
