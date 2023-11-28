package mk.finki.ukim.mk.lab.Model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class BookStore {
    public BookStore(String name, String city, String address) {
        this.Id = IdCounter;
        IdCounter++;
        this.name = name;
        this.city = city;
        this.address = address;
        bookList = new ArrayList<>();
    }
    public Book AddBook(Book book)
    {
        this.bookList.add(book);
        return book;
    }

    public void RemoveBook(Book book)
    {
        this.bookList.remove(book);
    }

    private Long Id;
    private static Long IdCounter = 0L;
    private String name;
    private String city;
    private String address;
    private List<Book> bookList;
}
