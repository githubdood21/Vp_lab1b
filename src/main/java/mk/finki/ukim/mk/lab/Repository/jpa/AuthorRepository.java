package mk.finki.ukim.mk.lab.Repository.jpa;

import mk.finki.ukim.mk.lab.DB.InMemoryDB;
import mk.finki.ukim.mk.lab.Model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

}
