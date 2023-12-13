package mk.finki.ukim.mk.lab.Repository.jpa;

import mk.finki.ukim.mk.lab.DB.InMemoryDB;
import mk.finki.ukim.mk.lab.Model.Author;
import mk.finki.ukim.mk.lab.Model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.stream.Collectors;

public interface BookRepository extends JpaRepository<Book, Long> {
    public Book findByIsbn(String isbn);
}
