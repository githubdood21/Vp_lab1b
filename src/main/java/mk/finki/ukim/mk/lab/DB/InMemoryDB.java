package mk.finki.ukim.mk.lab.DB;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.mk.lab.Model.Author;
import mk.finki.ukim.mk.lab.Model.Book;
import mk.finki.ukim.mk.lab.Model.BookStore;
import mk.finki.ukim.mk.lab.Repository.AuthorRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class InMemoryDB {
    public static final List<Author> authorList  = new ArrayList<>();
    public static final List<Book> bookList = new ArrayList<>();
    public static final List<BookStore> bookstoreList = new ArrayList<>();

    @PostConstruct
    public void init()
    {
        authorList.add(new Author(0L,"J. K.","Rowling","Joanne Rowling CH OBE FRSL, better known by her pen name J. K. Rowling, is a British author and philanthropist."));
        authorList.add(new Author(1L,"Stephen","King","Stephen Edwin King is an American author of horror, supernatural fiction, suspense, crime, science-fiction, and fantasy novels."));
        authorList.add(new Author(2L,"Charles","Dickens","Charles John Huffam Dickens was an English novelist and social critic who created some of the world's best-known fictional characters, and is regarded by many as the greatest novelist of the Victorian era."));
        authorList.add(new Author(3L,"William","Shakespeare","William Shakespeare was an English playwright, poet and actor. He is widely regarded as the greatest writer in the English language and the world's pre-eminent dramatist."));
        authorList.add(new Author(4L,"George","Orwell","Eric Arthur Blair, better known by his pen name George Orwell, was an English novelist, essayist, journalist, and critic. His work is characterised by lucid prose, social criticism, opposition to totalitarianism, and support of democratic socialism."));

        BookStore bs1 = new BookStore("Knizara1","Grad1","Addressa1");
        BookStore bs2 = new BookStore("Powell","Portland","Pearl District");
        BookStore bs3 = new BookStore("El Ateneo","Buenos","Aries");
        BookStore bs4 = new BookStore("Livaria","Porto","Manahuelo");
        BookStore bs5 = new BookStore("LoDo","Denver","Ernesto");


        Book b1 = new Book("0","Moby-Dick","Nautical fiction",1851, bs1);
        Book b2 = new Book("1","War and Peace","Novel",1865, bs2);
        Book b3 = new Book("2","Invisible Man","Fiction",1952, bs3);
        Book b4 = new Book("3","To Kill a Mockingbird","Novel",1960, bs4);
        Book b5 = new Book("4","Ulysses","Fiction",1918, bs5);

        bs1.AddBook(b1);
        bs2.AddBook(b2);
        bs3.AddBook(b3);
        bs4.AddBook(b4);
        bs5.AddBook(b5);

        bookstoreList.add(bs1);
        bookstoreList.add(bs2);
        bookstoreList.add(bs3);
        bookstoreList.add(bs4);
        bookstoreList.add(bs5);

        bookList.add(b1);
        bookList.add(b2);
        bookList.add(b3);
        bookList.add(b4);
        bookList.add(b5);


    }
}
