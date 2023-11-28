package mk.finki.ukim.mk.lab.Service;

import mk.finki.ukim.mk.lab.Model.Book;
import mk.finki.ukim.mk.lab.Model.BookStore;

import java.util.List;

public interface BookStoreService {
    public List<BookStore> findAll();
    public BookStore findById(Long Id);
}
