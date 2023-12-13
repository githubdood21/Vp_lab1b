package mk.finki.ukim.mk.lab.Model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "reviews")
public class Review {
    public Review(Integer score, String description, LocalDateTime timestamp) {
        this.score = score;
        this.description = description;
        this.timestamp = timestamp;
    }
    public Review() {

    }

    public void addBook(Book b)
    {
        this.book = b;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer score;
    private String description;
    @ManyToOne
    private Book book;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime timestamp;
}
