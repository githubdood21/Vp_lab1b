package mk.finki.ukim.mk.lab.Service.Implement;

import mk.finki.ukim.mk.lab.Model.Author;
import mk.finki.ukim.mk.lab.Model.Book;
import mk.finki.ukim.mk.lab.Model.BookStore;
import mk.finki.ukim.mk.lab.Repository.AuthorRepository;
import mk.finki.ukim.mk.lab.Repository.BookRepository;
import mk.finki.ukim.mk.lab.Repository.BookStoreRepository;
import mk.finki.ukim.mk.lab.Service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImplement implements BookService {

    private final BookStoreRepository bookStoreRepository;
    BookRepository bookRepository;
    AuthorRepository authorRepository;
    public BookServiceImplement(BookRepository bookRepository, AuthorRepository authorRepository, BookStoreRepository bookStoreRepository)
    {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.bookStoreRepository = bookStoreRepository;
    }

    @Override
    public Book saveBook(String isbn, String title, String genre, Integer year, Long bookStoreId) {

        if(isbn.isEmpty() || title.isEmpty() || genre.isEmpty() || year == null || bookStoreId == null)
        {
            //throw error ama me mrzi
            return new Book("","","",null);
        }
        BookStore bs = bookStoreRepository.findbyId(bookStoreId);
        if(bs == null)
        {
            //uste eden error
            return new Book("","","",null);
        }
        Book b = new Book(isbn, title, genre, year, bs);
        bs.AddBook(b);
        bookRepository.saveBook(b);
        return b;
    }

    @Override
    public Book editBook(String isbn, String title, String genre, Integer year, Long bookStoreId, Long Id) {
        Book origin = bookRepository.findById(Id);
        if(origin == null || isbn.isEmpty() || title.isEmpty() || genre.isEmpty() || year == null || bookStoreId == null)
        {
            //throw error ama me mrzi
            return new Book("","","",null);
        }
        BookStore bs = bookStoreRepository.findbyId(bookStoreId);
        if(bs == null)
        {
            //uste eden error
            return new Book("","","",null);
        }
        origin.getBookStore().RemoveBook(origin);

        origin.setIsbn(isbn);
        origin.setTitle(title);
        origin.setGenre(genre);
        origin.setYear(year);
        origin.setBookStore(bs);

        return origin;
    }

    @Override
    public void deleteBook(Long Id) {
        if(Id == null)
        {
            //throw error
            return;
        }
        Book b = bookRepository.findById(Id);
        if(b == null)
        {
            //throw error
            return;
        }
        bookRepository.deleteBook(b);
    }

    @Override
    public List<Book> listBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Author addAuthorToBook(Long authorId, String isbn) {
        Optional<Author> author = authorRepository.findById(authorId);
        Book book = bookRepository.findByIsbn(isbn);
        if(author.isPresent() && book != null)
        {
            return bookRepository.addAuthorToBook(author.get(), book);
        }
        return null;
    }

    @Override
    public Book findBookByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    @Override
    public Book findBookById(Long id) {
        return bookRepository.findById(id);
    }
}
