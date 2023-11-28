package mk.finki.ukim.mk.lab.Service.Implement;

import mk.finki.ukim.mk.lab.Model.Book;
import mk.finki.ukim.mk.lab.Model.BookStore;
import mk.finki.ukim.mk.lab.Repository.BookStoreRepository;
import mk.finki.ukim.mk.lab.Service.BookService;
import mk.finki.ukim.mk.lab.Service.BookStoreService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookStoreServiceImplement implements BookStoreService {
    private final BookStoreRepository bookStoreRepository;

    public BookStoreServiceImplement(BookStoreRepository bookStoreRepository) {
        this.bookStoreRepository = bookStoreRepository;
    }

    @Override
    public List<BookStore> findAll() {
        return bookStoreRepository.findAll();
    }

    @Override
    public BookStore findById(Long Id) {
        return bookStoreRepository.findbyId(Id);
    }
}
