package org.example;

import org.example.printers.OfficePrinter;
import org.example.printers.PhotoPrinter;
import org.example.printers.Xeros;

public class PrintService {
    public static void print(Strategy strategy) {
        switch (strategy) {
            case CHEAP -> OfficePrinter.print();
            case NORMAL -> Xeros.print();
            case VERY_EXPENSIVE -> PhotoPrinter.print();
            default -> System.out.println("Мы не специализируемся на такой печати :(");
        }
    }
}
