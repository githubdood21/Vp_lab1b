package mk.finki.ukim.mk.lab.Repository.old;

import mk.finki.ukim.mk.lab.DB.InMemoryDB;
import mk.finki.ukim.mk.lab.Model.Book;
import mk.finki.ukim.mk.lab.Model.BookStore;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class InMemoryBookStoreRepository {
    public BookStore findbyId(Long Id)
    {
        Optional<BookStore> result = InMemoryDB.bookstoreList.stream().filter(x -> x.getId().equals(Id)).collect(Collectors.toList()).stream().findFirst();
        return result.orElse(null);
    }
    public List<BookStore> findAll()
    {
        return InMemoryDB.bookstoreList;
    }
    public BookStore saveBookStore(BookStore bookstore)
    {
        if(InMemoryDB.bookstoreList.stream().anyMatch(x -> x.getId().equals(bookstore.getId())))
        {
            InMemoryDB.bookstoreList.removeIf(x -> x.getId().equals(bookstore.getId()));
        }
        InMemoryDB.bookstoreList.add(bookstore);
        return bookstore;
    }
    public BookStore editBookStore(BookStore bookstore)
    {
        BookStore bs = null;
        if(InMemoryDB.bookstoreList.stream().anyMatch(x -> x.getId().equals(bookstore.getId())))
        {
            bs = InMemoryDB.bookstoreList.stream().filter(x -> x.getId().equals(bookstore.getId())).collect(Collectors.toList()).stream().findFirst().get();
        }
        bs = bookstore;
        return bookstore;
    }
    public void deleteBookStore(BookStore bookstore)
    {
        InMemoryDB.bookstoreList.removeIf(x -> x.getId().equals(bookstore.getId()));
    }

    public Book addBookToStore(BookStore bookstore, Book book)
    {
        BookStore bs = null;
        if(InMemoryDB.bookstoreList.stream().anyMatch(x -> x.getId().equals(bookstore.getId())))
        {
            bs = InMemoryDB.bookstoreList.stream().filter(x -> x.getId().equals(bookstore.getId())).collect(Collectors.toList()).stream().findFirst().get();
        }
        if(bs.getBookList().stream().anyMatch(x -> x.getId().equals(book.getId())))
        {
            bs.getBookList().removeIf(x -> x.getId().equals(book.getId()));
        }
        book.getBookStore().RemoveBook(book);
        bs.AddBook(book);
        book.setBookStore(bs);
        return book;
    }
}
