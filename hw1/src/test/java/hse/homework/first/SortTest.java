package hse.homework.first;

import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class SortTest {

    @Test
    public void sortIfOneElement() {
        int[] arr1 = {1};
        int[] copy = Arrays.copyOf(arr1, arr1.length);
        Arrays.sort(copy);
        App.bubbleSort(arr1);
        assertTrue(Arrays.equals(arr1, copy));
    }

    @Test
    public void sortIfTwoElement() {
        int[] arr2 = {10, -5};
        int[] copy = Arrays.copyOf(arr2, arr2.length);
        Arrays.sort(copy);
        App.bubbleSort(arr2);
        assertTrue(Arrays.equals(arr2, copy));
    }

    @Test
    public void sortIfDescendingOrder() {
        int[] arr3 = {10, 8, 6, 4, 2};
        int[] copy = Arrays.copyOf(arr3, arr3.length);
        Arrays.sort(copy);
        App.bubbleSort(arr3);
        assertTrue(Arrays.equals(arr3, copy));
    }

    @Test
    public void sortIfIncreasingOrder() {
        int[] arr4 = {1, 2, 3, 4, 5};
        int[] copy = Arrays.copyOf(arr4, arr4.length);
        Arrays.sort(copy);
        App.bubbleSort(arr4);
        assertTrue(Arrays.equals(arr4, copy));
    }

    @Test
    public void sortIfNotSorted() {
        int[] arr5 = {8, -5, 3, 1, 100};
        int[] copy = Arrays.copyOf(arr5, arr5.length);
        Arrays.sort(copy);
        App.bubbleSort(arr5);
        assertTrue(Arrays.equals(arr5, copy));
    }
}
