package mk.finki.ukim.mk.lab.Repository;

import mk.finki.ukim.mk.lab.DB.InMemoryDB;
import mk.finki.ukim.mk.lab.Model.Author;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class AuthorRepository {
    public List<Author> findAll()
    {
        return InMemoryDB.authorList;
    }
    public Optional<Author> findById(Long id)
    {
        return InMemoryDB.authorList.stream().filter(x -> x.getId().equals(id)).collect(Collectors.toList()).stream().findFirst();
    }
}
