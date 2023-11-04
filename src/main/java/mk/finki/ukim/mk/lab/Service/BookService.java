package mk.finki.ukim.mk.lab.Service;

import mk.finki.ukim.mk.lab.Model.Author;
import mk.finki.ukim.mk.lab.Model.Book;

import java.util.List;

public interface BookService {
    List<Book> listBooks();
    Author addAuthorToBook(Long authorId, String isbn);
    Book findBookByIsbn(String isbn);
}
