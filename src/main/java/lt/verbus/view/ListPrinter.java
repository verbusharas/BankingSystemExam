package lt.verbus.view;

import java.util.List;

public class ListPrinter {

    public static <T> void printNumeratedList(List<T> list) {
        for (int i = 1; i <=list.size(); i++) {
            System.out.println(ConsoleColor.GREEN + i + ". " + list.get(i-1) + ConsoleColor.DEFAULT);
        }
    }
}
