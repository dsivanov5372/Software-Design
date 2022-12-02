package org.example;

import java.io.File;

public class Main {
    public static void main(String[] args) {

        FileIterator iterator = new FileIterator("/Users/dmitry/Documents/projects/FileIterator/" +
                                                        "src/main/resources/test.txt");
        iterator.forEachRemaining(System.out::println);

        while (iterator.hasNext()) {
            iterator.next();
            iterator.remove();
        }

        System.out.println("\nОчищенный итератор\n");
        iterator.forEachRemaining(System.out::println);

        FileIterator iterator1 = new FileIterator(
                new File("/Users/dmitry/Documents/projects/FileIterator/" +
                "src/main/resources/test.txt"));

        System.out.println(iterator1.next());

        FileIterator iterator2 = new FileIterator("/someFolder/test.txt");
        System.out.println(iterator2.hasNext());
    }
}