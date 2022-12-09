package org.example.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode
public class Book implements Comparable<Book> {
    String title;
    String author;

    @Override
    public int compareTo(Book book) {
        if (book == null) {
            return 1;
        }
        int result = title.compareTo(book.title);
        if (result == 0) {
            return author.compareTo(book.author);
        }
        return result;
    }
}
