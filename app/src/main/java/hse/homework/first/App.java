package hse.homework.first;

import java.util.Scanner;

public class App {
    public static void bubbleSort(int[] array) {
        for (int i = 0; i < array.length; ++i) {
            for (int j = i + 1; j < array.length; ++j) {
                if (array[j] < array[i]) {
                    int bucket = array[j];
                    array[j] = array[i];
                    array[i] = bucket;
                }
            }
        }
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Input size of array:");
            int size = Integer.parseInt(scanner.next());

            if (size < 0) {
                throw new IllegalCallerException("Wrong array size!");
            } 

            int[] array = new int[size];
            for (int i = 0; i < size; ++i) {
                array[i] = Integer.parseInt(scanner.next());
            }

            bubbleSort(array);

            System.out.println("Sorted array:");
            for (int element : array) {
                System.out.print(element + " ");
            }
            System.out.println();
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
