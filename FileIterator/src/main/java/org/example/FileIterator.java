package org.example;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

public class FileIterator implements Iterator<String> {
    List<String> lines;
    int index = 0;

    public FileIterator(File file) {
        readFile(file);
    }

    public FileIterator(String path) {
        File file = new File(path);
        readFile(file);
    }

    private void readFile(File file) {
        if (file.exists() && file.isFile()) {
            try {
                lines = Files.readAllLines(Path.of(file.getPath()), StandardCharsets.UTF_8);
            } catch (IOException ignored) {
            }
        } else {
            lines = new ArrayList<>();
        }
    }


    @Override
    public boolean hasNext() {
        return index < lines.size() && lines.get(index) != null;
    }

    @Override
    public String next() throws NoSuchElementException {
        return lines.get(index++);
    }

    @Override
    public void forEachRemaining(Consumer<? super String> action) {
        lines.forEach(action);
    }

    // Removes from the underlying collection the last element returned by this iterator (optional operation).
    @Override
    public void remove() throws NoSuchElementException {
        if(index != 0 && index <= lines.size()) {
            lines.remove(--index);
        } else {
            throw new NoSuchElementException();
        }
    }
}
