package mk.finki.ukim.mk.lab.Model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Book {
    public Book(String isbn, String title, String genre, Integer year)
    {
        this.isbn = isbn;
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.authors = new ArrayList<>();
    }
    public void addAuthor(Author a)
    {
        authors.add(a);
    }
    private String isbn;
    private String title;
    private String genre;
    private Integer year;
    private List<Author> authors;
}
