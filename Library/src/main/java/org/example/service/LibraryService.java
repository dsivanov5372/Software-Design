package org.example.service;

import org.example.Main;
import org.example.model.Book;
import org.example.model.Library;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LibraryService {
    private final Library library;
    private final List<Book> broughtBooks;

    public LibraryService(String path) throws FileNotFoundException {
        File file = new File(path);
        library = new Library(file);
        broughtBooks = new ArrayList<>();
    }

    public LibraryService() {
        library = new Library();
        broughtBooks = new ArrayList<>();
    }

    private void printBooks(List<Book> books, String message) {
        System.out.println(message);
        for (int i = 0; i < books.size(); ++i) {
            Book book = books.get(i);
            System.out.println((i + 1) + ". " + book.getAuthor() + ": " + book.getTitle());
        }
    }

    public void getBook() {
        System.out.println("Введите название книги:");
        String title = Main.scanner.nextLine();
        List<Book> books = library.getBookList(title);

        if (books.isEmpty()) {
            System.out.println("Книги с таким названием нет :(");
        } else {
            printBooks(books, "Доступные книги:");
            MenuService.printBookOptions();
            while (true) {
                int number = Integer.parseInt(Main.scanner.next());
                Main.scanner.nextLine(); // убирает пустое пространство
                if (number == 0) {
                    break;
                } else if (number >= 1 && number <= books.size()) {
                    library.removeBook(books.get(number - 1));
                    broughtBooks.add(books.get(number - 1));
                    break;
                } else {
                    System.out.println("Неверная опция. Введите номер снова:");
                }
            }
        }
    }

    public void getBroughtBooksList() {
        printBooks(broughtBooks, "Взятые вами книги:");
    }

    public void putBook() {
        MenuService.printPutBookOptions();
        while (true) {
            int command = Integer.parseInt(Main.scanner.next());
            Main.scanner.nextLine(); // убирает пустое пространство
            switch (command) {
                case 1 -> {
                    if (broughtBooks.isEmpty()) {
                        System.out.println("Вы ещё не взяли ни одну книгу.");
                    } else {
                        getBroughtBooksList();
                        MenuService.printBookOptions();
                        while (true) {
                            int number = Integer.parseInt(Main.scanner.next());
                            if (number == 0) {
                                break;
                            } else if (number >= 1 && number <= broughtBooks.size()) {
                                library.put(broughtBooks.get(number - 1));
                                broughtBooks.remove(number - 1);
                            } else {
                                System.out.println("Неверная опция. Введите номер снова:");
                            }
                        }
                    }
                    return;
                }
                case 2 -> {
                    System.out.println("Введите название книги:");
                    String title = Main.scanner.nextLine();
                    System.out.println("Введите автора книги:");
                    String author = Main.scanner.nextLine();
                    library.put(Book.builder().author(author).title(title).build());
                    return;
                }
                default -> System.out.println("Неверная опция. Введите номер комманды снова:");
            }
        }
    }

    public void getAvailableBooks() {
        System.out.println("Cписок доступных книг в библиотеке:");
        Map<Book, Integer> books = library.getBooks();
        int i = 1;
        for (Map.Entry<Book, Integer> entry : books.entrySet()) {
            String author = entry.getKey().getAuthor();
            String title = entry.getKey().getTitle();
            System.out.println((i + 1) + ". " + author + ": " + title + " - " + entry.getValue() + " шт.");
        }
    }
}
