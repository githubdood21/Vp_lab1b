package mk.finki.ukim.mk.lab.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "books")
public class Book {
    public Book(String isbn, String title, String genre, Integer year)
    {
        this.isbn = isbn;
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.authors = new ArrayList<>();
        this.reviews = new ArrayList<>();
    }
    public Book(String isbn, String title, String genre, Integer year, BookStore bookStore)
    {
        this(isbn, title, genre, year);
        this.bookStore = bookStore;
    }

    public Book() {

    }

    public void addAuthor(Author a)
    {
        authors.add(a);
    }
    public void addReview(Review r) { reviews.add(r); }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String isbn;
    private String title;
    private String genre;
    @Column(name = "book_year")
    private Integer year;
    @OneToMany(mappedBy = "book", fetch = FetchType.EAGER)
    private List<Author> authors;
    @ManyToOne
    private BookStore bookStore;
    @OneToMany(mappedBy = "book", fetch = FetchType.EAGER)
    private List<Review> reviews;
}
