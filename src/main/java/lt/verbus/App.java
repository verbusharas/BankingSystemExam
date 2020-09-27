package lt.verbus;

import lt.verbus.controller.Controller;
import lt.verbus.exception.UserNotFoundException;
import lt.verbus.model.BankAccount;
import lt.verbus.model.CardType;
import lt.verbus.model.User;
import lt.verbus.repository.*;
import lt.verbus.services.BankAccountService;
import lt.verbus.services.PrinterService;
import lt.verbus.services.UserService;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class App {
    public static void main(String[] args) throws IOException, SQLException, UserNotFoundException {

        new Controller().launchApp();
//        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//        System.out.println(timestamp.toString());

        ConnectionPool.closeConnections();
    }


}
