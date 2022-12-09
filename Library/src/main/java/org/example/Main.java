package org.example;

import org.example.service.LibraryService;
import org.example.service.MenuService;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static final Scanner scanner = new Scanner(System.in);

    public static LibraryService getService() throws FileNotFoundException {
        System.out.println("Укажите полный путь до файла:");
        String path = scanner.next();
        scanner.nextLine(); // убирает пустое пространство
        return new LibraryService(path);
    }

    public static void main(String[] args) {
        MenuService.printInitializeOptions();
        LibraryService service = null;

        try {
            int command = Integer.parseInt(scanner.next());
            while (service == null) {
                String path = "/Users/dmitry/Documents/projects/Library/src/main/resources/books.json";
                switch (command) {
                    case 1 -> service = new LibraryService();
                    case 2 -> service = getService();
                    case 3 -> service = new LibraryService(path);
                    default -> System.out.println("Неверная опция. Попробуйте снова.");
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Неверный путь до файла. Аварийный выход из программы...");
            return;
        }

        while (true) {
            MenuService.printMenu();
            String command = scanner.next();
            scanner.nextLine(); // убирает пустое пространство
            switch (command) {
                case "/get" -> service.getBook();
                case "/list" -> service.getBroughtBooksList();
                case "/put" -> service.putBook();
                case "/all" -> service.getAvailableBooks();
                case "/exit" -> {
                    return;
                }
                default -> System.out.println("Неверная опция. Попробуйте снова.");
            }
        }
    }
}