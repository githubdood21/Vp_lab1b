package mk.finki.ukim.mk.lab.Service.Implement;

import mk.finki.ukim.mk.lab.Model.Author;
import mk.finki.ukim.mk.lab.Model.Book;
import mk.finki.ukim.mk.lab.Repository.AuthorRepository;
import mk.finki.ukim.mk.lab.Repository.BookRepository;
import mk.finki.ukim.mk.lab.Service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImplement implements BookService {

    BookRepository bookRepository;
    AuthorRepository authorRepository;
    public BookServiceImplement(BookRepository bookRepository, AuthorRepository authorRepository)
    {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
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
}
