package mk.finki.ukim.mk.lab.Service;

import mk.finki.ukim.mk.lab.Model.Author;
import mk.finki.ukim.mk.lab.Model.Book;
import mk.finki.ukim.mk.lab.Model.BookStore;

import java.util.List;

public interface BookService {
    List<Book> listBooks();
    Author addAuthorToBook(Long authorId, String isbn);
    Book findBookByIsbn(String isbn);
    Book findBookById(Long id);
    Book saveBook(String isbn, String title, String genre, Integer year, Long bookStoreId);
    Book editBook(String isbn, String title, String genre, Integer year, Long bookStoreId, Long Id);
    void deleteBook(Long Id);
}
