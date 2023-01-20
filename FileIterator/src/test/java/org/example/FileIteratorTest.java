package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Test;

public class FileIteratorTest {
    private final String ABSOLUTE_PATH_TO_FOLDER = "/Users/dmitry/Documents/projects/Software-Design/FileIterator/src/main/resources/";

    @Test
    public void doesNotHaveNextIfFileDoesNotExists() {
        FileIterator iterator = new FileIterator(new File(ABSOLUTE_PATH_TO_FOLDER + "test1.txt"));
        assertFalse(iterator.hasNext());
    }

    @Test
    public void doesNotHaveNextIfFileIsEmpty() {
        FileIterator iterator = new FileIterator(new File(ABSOLUTE_PATH_TO_FOLDER + "empty.txt"));
        assertFalse(iterator.hasNext());
    }

    @Test
    public void nextMethodThrowsExceptionIfFileIsEmpty() {
        FileIterator iterator = new FileIterator(new File(ABSOLUTE_PATH_TO_FOLDER + "empty.txt"));
        assertThrows(RuntimeException.class, () -> iterator.next());
    }

    @Test
    public void removeMethodThrowsExceptionIfFileIsEmpty() {
        FileIterator iterator = new FileIterator(new File(ABSOLUTE_PATH_TO_FOLDER + "empty.txt"));
        assertThrows(RuntimeException.class, () -> iterator.remove());
    }

    @Test
    public void addSubstringToEndOfAllStrings() {
        FileIterator iterator = new FileIterator(new File(ABSOLUTE_PATH_TO_FOLDER + "test.txt"));
        List<String> strings = new ArrayList<>();
        iterator.forEachRemaining(str -> strings.add(str));
        
        Iterator<String> listIterator = strings.iterator();
        while (iterator.hasNext()) {
            assertEquals(iterator.next(), listIterator.next());
        }
    }

    @Test
    public void removedLastRequstedElement() {
        FileIterator iterator = new FileIterator(new File(ABSOLUTE_PATH_TO_FOLDER + "test.txt"));
        String toDelete = iterator.next();
        iterator.remove();

        List<String> strings = new ArrayList<>();
        iterator.forEachRemaining(str -> strings.add(str));
        assertFalse(strings.contains(toDelete));
    }
}