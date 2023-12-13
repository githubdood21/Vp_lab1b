package mk.finki.ukim.mk.lab.Service.Implement;

import mk.finki.ukim.mk.lab.Model.Book;
import mk.finki.ukim.mk.lab.Model.BookStore;
import mk.finki.ukim.mk.lab.Repository.jpa.BookRepository;
import mk.finki.ukim.mk.lab.Repository.jpa.BookStoreRepository;
import mk.finki.ukim.mk.lab.Service.BookStoreService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookStoreServiceImplement implements BookStoreService {
    private final BookStoreRepository bookStoreRepository;
    private final BookRepository bookRepository;

    public BookStoreServiceImplement(BookStoreRepository bookStoreRepository, BookRepository bookRepository) {
        this.bookStoreRepository = bookStoreRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public List<BookStore> findAll() {
        return bookStoreRepository.findAll();
    }

    @Override
    public BookStore findById(Long Id) {
        Optional<BookStore> bs = bookStoreRepository.findById(Id);
        if(bs.isEmpty())
        {
            return null;
        }
        return bs.get();
    }

    public BookStore saveBookStore(String name, String city, String address) {

        if(name.isEmpty() || city.isEmpty() || address.isEmpty())
        {
            //throw error ama me mrzi
            return null;
        }
        BookStore bs = new BookStore(name, city, address);
        bookStoreRepository.save(bs);
        return bs;
    }

    @Override
    public BookStore editBookStore(String name, String city, String address, Long Id) {
        if(Id == null)
        {
            //throw error ama me mrzi
            return null;
        }
        Optional<BookStore> origin = bookStoreRepository.findById(Id);
        if(origin.isEmpty() || name.isEmpty() || city.isEmpty() || address.isEmpty())
        {
            //throw error ama me mrzi
            return null;
        }

        origin.get().setName(name);
        origin.get().setCity(city);
        origin.get().setAddress(address);

        bookStoreRepository.flush();

        return origin.get();
    }

    @Override
    public void deleteBookStore(Long Id) {
        if(Id == null)
        {
            //throw error
            return;
        }
        Optional<BookStore> b = bookStoreRepository.findById(Id);
        if(b.isEmpty())
        {
            //throw error
            return;
        }
        b.get().getBookList().stream().forEach(x -> x.setBookStore(null));
        bookStoreRepository.delete(b.get());
    }

    @Override
    public Book addBookToStore(Long BookStoreId, Long BookId)
    {
        if(BookStoreId == null || BookId == null)
        {
            //error
            return null;
        }
        Optional<Book> b = bookRepository.findById(BookId);
        Optional<BookStore> bs = bookStoreRepository.findById(BookStoreId);
        if(b.isEmpty() || bs.isEmpty())
        {
            //error
            return null;
        }

        bs.get().getBookList().removeIf(x -> x.getId().equals(b.get().getId()));
        b.get().getBookStore().RemoveBook(b.get());
        bs.get().AddBook(b.get());
        b.get().setBookStore(bs.get());

        return b.get();
    }

    @Override
    public List<BookStore> listBookStore() {
        return bookStoreRepository.findAll();
    }
}
