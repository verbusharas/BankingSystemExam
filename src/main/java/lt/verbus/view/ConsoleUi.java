package lt.verbus.view;

import lt.verbus.model.User;

public class ConsoleUi {

    public static final String FALSE_MENU_ENTRY = "(!) Please choose from listed menu items";


    public static void displayMainMenu(){

    }

    public static void displayLoginMenu(){

    }

    public static void displayRegisterMenu(){

    }



    public static void displayTopUpMenu(){

    }

    public static void displayWithdrawMenu(){

    }

    public static void displaySendAmountMenu(){
        System.out.println("---- TRANSFER MONEY ----");
        System.out.println("[0] Cancel");
        System.out.println("Enter the amount (EUR):");
    }

    public static void displayReceiverMenu(){
        System.out.println("[0] Cancel");
        System.out.println("Enter username of the receiver:");
    }



}
