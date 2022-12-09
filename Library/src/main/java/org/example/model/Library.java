package org.example.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Getter
@NoArgsConstructor
public class Library {
    private final Map<Book, Integer> books = new TreeMap<>();

    public Library(File file) throws FileNotFoundException {
        if (!file.exists() || !file.isFile()) {
            throw new FileNotFoundException("File not found or it is a directory!");
        }

        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new FileReader(file));
        Type listType = new TypeToken<List<Book>>(){}.getType();
        List<Book> bookList = gson.fromJson(reader, listType);
        addAll(bookList);
    }

    private void addAll(List<Book> bookList) {
        bookList.forEach(book -> books.merge(book, 1, Integer::sum));
    }

    public List<Book> getBookList(String title) {
        List<Book> bookList = new ArrayList<>();
        books.keySet().stream()
                      .filter(book -> book.getTitle().equals(title))
                      .forEach(bookList::add);
        return bookList;
    }

    public void put(Book book) {
        books.merge(book, 1, Integer::sum);
    }

    public void removeBook(Book book) {
        Integer value = books.get(book);
        if (value != null) {
            if (value == 1) {
                books.remove(book);
            } else {
                books.put(book, value - 1);
            }
        }
    }
}
