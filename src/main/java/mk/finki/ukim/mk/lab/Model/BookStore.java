package mk.finki.ukim.mk.lab.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "bookstores")
public class BookStore {
    public BookStore(String name, String city, String address) {
        this.name = name;
        this.city = city;
        this.address = address;
        bookList = new ArrayList<>();
    }
    public BookStore() {

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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String name;
    private String city;
    private String address;
    @OneToMany(mappedBy = "bookStore", fetch = FetchType.EAGER)
    private List<Book> bookList;
}
