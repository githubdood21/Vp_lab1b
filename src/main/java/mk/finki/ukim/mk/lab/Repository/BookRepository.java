package mk.finki.ukim.mk.lab.Repository;

import mk.finki.ukim.mk.lab.DB.InMemoryDB;
import mk.finki.ukim.mk.lab.Model.Author;
import mk.finki.ukim.mk.lab.Model.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class BookRepository {
    public List<Book> findAll()
    {
        return InMemoryDB.bookList;
    }
    public Book findByIsbn(String isbn)
    {
        Optional<Book> result = InMemoryDB.bookList.stream().filter(x -> x.getIsbn().equals(isbn)).collect(Collectors.toList()).stream().findFirst();
        return result.orElse(null);
    }
    public Author addAuthorToBook(Author author, Book book)
    {
        Optional<Book> result = InMemoryDB.bookList.stream().filter(x -> x.equals(book)).collect(Collectors.toList()).stream().findFirst();
        if(result.isPresent())
        {
            result.get().addAuthor(author);
        }
        return author;
    }
}
