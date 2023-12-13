package mk.finki.ukim.mk.lab.Service;

import mk.finki.ukim.mk.lab.Model.Author;
import mk.finki.ukim.mk.lab.Model.Book;

import java.time.LocalDate;
import java.util.List;

public interface AuthorService {
    List<Author> listAuthors();
    Author findById(Long id);
    Author saveAuthor(String name, String surname, String biography, LocalDate dateOfBirth);
    Author editAuthor(String name, String surname, String biography, LocalDate dateOfBirth, Long Id);
    public void deleteAuthor(Long Id);
}
