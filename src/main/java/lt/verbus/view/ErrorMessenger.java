package lt.verbus.view;

public class ErrorMessenger {

    public static void warnAboutFalseMenuEntry(){
        System.out.println(ConsoleColor.RED + "ERROR: Please choose from listed menu items" + ConsoleColor.DEFAULT);
    }

    public static void warnAboutUserNotFound(String username){
        System.out.println(ConsoleColor.RED + "ERROR: User with username \'" + username + "\' not found. " + ConsoleColor.DEFAULT);
    }

    public static void warnAboutInsufficientFunds(){
        System.out.println(ConsoleColor.RED + "ERROR: Could not complete operation. Insufficient funds." + ConsoleColor.DEFAULT);
    }

}
