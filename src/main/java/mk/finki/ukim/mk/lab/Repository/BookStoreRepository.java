package mk.finki.ukim.mk.lab.Repository;

import mk.finki.ukim.mk.lab.DB.InMemoryDB;
import mk.finki.ukim.mk.lab.Model.Book;
import mk.finki.ukim.mk.lab.Model.BookStore;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class BookStoreRepository {
    public BookStore findbyId(Long Id)
    {
        Optional<BookStore> result = InMemoryDB.bookstoreList.stream().filter(x -> x.getId().equals(Id)).collect(Collectors.toList()).stream().findFirst();
        return result.orElse(null);
    }
    public List<BookStore> findAll()
    {
        return InMemoryDB.bookstoreList;
    }
}
