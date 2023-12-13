package mk.finki.ukim.mk.lab.Repository.jpa;

import mk.finki.ukim.mk.lab.DB.InMemoryDB;
import mk.finki.ukim.mk.lab.Model.Author;
import mk.finki.ukim.mk.lab.Model.Book;
import mk.finki.ukim.mk.lab.Model.BookStore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public interface BookStoreRepository extends JpaRepository<BookStore, Long> {

}
