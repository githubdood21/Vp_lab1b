package mk.finki.ukim.mk.lab.DB;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.mk.lab.Model.Author;
import mk.finki.ukim.mk.lab.Model.Book;
import mk.finki.ukim.mk.lab.Repository.AuthorRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class InMemoryDB {
    public static final List<Author> authorList  = new ArrayList<>();
    public static final List<Book> bookList = new ArrayList<>();

    @PostConstruct
    public void init()
    {
        authorList.add(new Author(0L,"J. K.","Rowling","Joanne Rowling CH OBE FRSL, better known by her pen name J. K. Rowling, is a British author and philanthropist."));
        authorList.add(new Author(1L,"Stephen","King","Stephen Edwin King is an American author of horror, supernatural fiction, suspense, crime, science-fiction, and fantasy novels."));
        authorList.add(new Author(2L,"Charles","Dickens","Charles John Huffam Dickens was an English novelist and social critic who created some of the world's best-known fictional characters, and is regarded by many as the greatest novelist of the Victorian era."));
        authorList.add(new Author(3L,"William","Shakespeare","William Shakespeare was an English playwright, poet and actor. He is widely regarded as the greatest writer in the English language and the world's pre-eminent dramatist."));
        authorList.add(new Author(4L,"George","Orwell","Eric Arthur Blair, better known by his pen name George Orwell, was an English novelist, essayist, journalist, and critic. His work is characterised by lucid prose, social criticism, opposition to totalitarianism, and support of democratic socialism."));

        bookList.add(new Book("0","Moby-Dick","Nautical fiction",1851));
        bookList.add(new Book("1","War and Peace","Novel",1865));
        bookList.add(new Book("2","Invisible Man","Fiction",1952));
        bookList.add(new Book("3","To Kill a Mockingbird","Novel",1960));
        bookList.add(new Book("4","Ulysses","Fiction",1918));
    }
}
