package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Выберите тип печати:");
        System.out.println("1) На обычной бумаге");
        System.out.println("2) На ватмане");
        System.out.println("3) Фотография из текста");
        int type = Integer.parseInt(scanner.next());
        switch (type) {
            case 1 -> PrintService.print(Strategy.CHEAP);
            case 2 -> PrintService.print(Strategy.NORMAL);
            case 3 -> PrintService.print(Strategy.VERY_EXPENSIVE);
            default -> System.out.println("Мы не специализируемся на такой печати :(");
        }
    }
}