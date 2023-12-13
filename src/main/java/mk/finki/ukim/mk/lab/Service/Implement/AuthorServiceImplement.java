package mk.finki.ukim.mk.lab.Service.Implement;

import mk.finki.ukim.mk.lab.Model.Author;
import mk.finki.ukim.mk.lab.Model.Book;
import mk.finki.ukim.mk.lab.Model.BookStore;
import mk.finki.ukim.mk.lab.Repository.jpa.AuthorRepository;
import mk.finki.ukim.mk.lab.Service.AuthorService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImplement implements AuthorService {
    private final AuthorRepository authorRepository;
    public AuthorServiceImplement(AuthorRepository authorRepository)
    {
        this.authorRepository = authorRepository;
    }
    @Override
    public List<Author> listAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Author findById(Long id) {
        return authorRepository.findById(id).get();
    }

    @Override
    public Author saveAuthor(String name, String surname, String biography, LocalDate dateOfBirth) {
        if(name.isEmpty() || surname.isEmpty() || biography.isEmpty() || dateOfBirth == null)
        {
            //throw error ama me mrzi
            return null;
        }
        Author a = new Author(name, surname, biography, dateOfBirth);
        authorRepository.save(a);
        return a;
    }

    @Override
    public Author editAuthor(String name, String surname, String biography, LocalDate dateOfBirth, Long Id) {
        if(Id == null)
        {
            //throw error ama me mrzi
            return null;
        }
        Optional<Author> origin = authorRepository.findById(Id);
        if(origin.isEmpty() || name.isEmpty() || surname.isEmpty() || biography.isEmpty() || dateOfBirth == null)
        {
            //throw error ama me mrzi
            return null;
        }

        origin.get().setName(name);
        origin.get().setSurname(surname);
        origin.get().setBiography(biography);
        origin.get().setDateOfBirth(dateOfBirth);

        authorRepository.flush();

        return origin.get();
    }

    @Override
    public void deleteAuthor(Long Id) {
        if(Id == null)
        {
            //throw error
            return;
        }
        Optional<Author> r = authorRepository.findById(Id);
        if(r.isEmpty())
        {
            //throw error
            return;
        }
        r.get().getBook().getAuthors().removeIf(x -> x.getId().equals(r.get().getId()));
        authorRepository.delete(r.get());
    }
}
