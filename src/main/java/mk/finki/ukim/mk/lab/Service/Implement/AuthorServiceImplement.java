package mk.finki.ukim.mk.lab.Service.Implement;

import mk.finki.ukim.mk.lab.Model.Author;
import mk.finki.ukim.mk.lab.Repository.AuthorRepository;
import mk.finki.ukim.mk.lab.Service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImplement implements AuthorService {
    AuthorRepository authorRepository;
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
}
