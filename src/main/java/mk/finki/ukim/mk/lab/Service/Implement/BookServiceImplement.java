package mk.finki.ukim.mk.lab.Service.Implement;

import mk.finki.ukim.mk.lab.Model.Author;
import mk.finki.ukim.mk.lab.Model.Book;
import mk.finki.ukim.mk.lab.Model.BookStore;
import mk.finki.ukim.mk.lab.Model.Review;
import mk.finki.ukim.mk.lab.Repository.jpa.AuthorRepository;
import mk.finki.ukim.mk.lab.Repository.jpa.BookRepository;
import mk.finki.ukim.mk.lab.Repository.jpa.BookStoreRepository;
import mk.finki.ukim.mk.lab.Repository.jpa.ReviewRepository;
import mk.finki.ukim.mk.lab.Service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImplement implements BookService {

    private final BookStoreRepository bookStoreRepository;
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final ReviewRepository reviewRepository;

    public BookServiceImplement(BookRepository bookRepository, AuthorRepository authorRepository, BookStoreRepository bookStoreRepository, ReviewRepository reviewRepository)
    {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.bookStoreRepository = bookStoreRepository;
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Book saveBook(String isbn, String title, String genre, Integer year, Long bookStoreId) {

        if(isbn.isEmpty() || title.isEmpty() || genre.isEmpty() || year == null || bookStoreId == null)
        {
            //throw error ama me mrzi
            return null;
        }
        Optional<BookStore> bs = bookStoreRepository.findById(bookStoreId);
        if(bs.isEmpty())
        {
            //uste eden error
            return null;
        }
        Book b = new Book(isbn, title, genre, year, bs.get());
        bs.get().AddBook(b);
        bookRepository.save(b);
        return b;
    }

    @Override
    public Book editBook(String isbn, String title, String genre, Integer year, Long bookStoreId, Long Id) {
        if(Id == null)
        {
            //throw error ama me mrzi
            return null;
        }
        Optional<Book> origin = bookRepository.findById(Id);
        if(origin.isEmpty() || isbn.isEmpty() || title.isEmpty() || genre.isEmpty() || year == null || bookStoreId == null)
        {
            //throw error ama me mrzi
            return null;
        }
        Optional<BookStore> bs = bookStoreRepository.findById(bookStoreId);
        if(bs.isEmpty())
        {
            //uste eden error
            return null;
        }
        origin.get().getBookStore().RemoveBook(origin.get());

        origin.get().setIsbn(isbn);
        origin.get().setTitle(title);
        origin.get().setGenre(genre);
        origin.get().setYear(year);
        origin.get().setBookStore(bs.get());

        bookRepository.flush();

        return origin.get();
    }

    @Override
    public void deleteBook(Long Id) {
        if(Id == null)
        {
            //throw error
            return;
        }
        Optional<Book> b = bookRepository.findById(Id);
        if(b.isEmpty())
        {
            //throw error
            return;
        }
        b.get().getAuthors().forEach(x -> x.setBook(null));
        reviewRepository.deleteAll(b.get().getReviews());
        b.get().getBookStore().getBookList().removeIf(x -> x.getId().equals(b.get().getId()));
        bookRepository.delete(b.get());

        bookRepository.flush();
        reviewRepository.flush();
        bookStoreRepository.flush();
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
            book.getAuthors().removeIf(x -> x.getId().equals(author.get().getId()));
            book.addAuthor(author.get());
            author.get().AddBook(book);
            bookRepository.flush();
            authorRepository.flush();
            return author.get();
        }
        return null;
    }
    @Override
    public Review addReviewToBook(Long reviewId, Long bookId) {
        Optional<Review> review = reviewRepository.findById(reviewId);
        Optional<Book> book = bookRepository.findById(bookId);
        if(review.isPresent() && book.isPresent())
        {
            book.get().getReviews().removeIf(x -> x.getId().equals(review.get().getId()));
            book.get().addReview(review.get());
            review.get().addBook(book.get());
            bookRepository.flush();
            reviewRepository.flush();
            return review.get();
        }
        return null;
    }
    @Override
    public Book findBookByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    @Override
    public Book findBookById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if(book.isEmpty())
        {
            return null;
        }
        return book.get();
    }
}
