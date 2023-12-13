package mk.finki.ukim.mk.lab.Model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "authors")
public class Author {
    public Author(Long id, String name, String surname, String biography)
    {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.biography = biography;
    }

    public Author(String name, String surname, String biography, LocalDate dateOfBirth) {
        this.name = name;
        this.surname = surname;
        this.biography = biography;
        this.dateOfBirth = dateOfBirth;
    }

    public void AddBook(Book book)
    {
        this.book = book;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String biography;
    @ManyToOne
    private Book book;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateOfBirth;

    public Author() {

    }
}
