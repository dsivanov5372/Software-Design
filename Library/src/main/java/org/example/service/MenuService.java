package org.example.service;

public class MenuService {
    public static void printMenu() {
        System.out.println("Доступные действия:");
        System.out.println("/get (взять книгу с определённым названием)");
        System.out.println("/list (показать список взятых книг)");
        System.out.println("/put (вернуть/положить новую книгу в библиотеку)");
        System.out.println("/all (показать список и количество книг)");
        System.out.println("/exit (выход из программы)");
    }

    public static void printInitializeOptions() {
        System.out.println("Способы создания библиотеки:");
        System.out.println("1. Cоздать пустую библиотеку");
        System.out.println("2. Задать книги из json файла (указав путь)");
        System.out.println("3. Задать книги из json файла по умолчанию");
    }

    public static void printBookOptions() {
        System.out.println("Выберите номер книги, которую хотите взять(вернуть);");
        System.out.println("Иначе напечатайте 0.");
    }

    public static void printPutBookOptions() {
        System.out.println("Какую книгу вы хотите положить в библиотеку?");
        System.out.println("1. Ту, которую взял из библиотеки");
        System.out.println("2. Новую");
    }
}
