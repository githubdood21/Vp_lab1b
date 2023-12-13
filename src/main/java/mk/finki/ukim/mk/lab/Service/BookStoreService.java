package mk.finki.ukim.mk.lab.Service;

import mk.finki.ukim.mk.lab.Model.Book;
import mk.finki.ukim.mk.lab.Model.BookStore;

import java.util.List;

public interface BookStoreService {
    public List<BookStore> findAll();
    public BookStore findById(Long Id);
    public List<BookStore> listBookStore();
    public void deleteBookStore(Long Id);
    public BookStore editBookStore(String name, String city, String address, Long Id);
    public BookStore saveBookStore(String name, String city, String address);
    public Book addBookToStore(Long BookStoreId, Long BookId);
}
