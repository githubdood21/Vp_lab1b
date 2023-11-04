package mk.finki.ukim.mk.lab.Service;

import mk.finki.ukim.mk.lab.Model.Author;

import java.util.List;

public interface AuthorService {
    List<Author> listAuthors();
    Author findById(Long id);
}
