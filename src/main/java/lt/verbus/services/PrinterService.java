package lt.verbus.services;

import org.w3c.dom.ls.LSOutput;

import java.util.List;

public class PrinterService {
    public <T> void printListToConsole(List<T> list) {
        list.forEach(System.out::println);
    }
}
