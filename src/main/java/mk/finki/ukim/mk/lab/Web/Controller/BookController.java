package mk.finki.ukim.mk.lab.Web.Controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.finki.ukim.mk.lab.Model.Book;
import mk.finki.ukim.mk.lab.Service.Implement.AuthorServiceImplement;
import mk.finki.ukim.mk.lab.Service.Implement.BookServiceImplement;
import mk.finki.ukim.mk.lab.Service.Implement.BookStoreServiceImplement;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BookController {
    private final AuthorServiceImplement authorServiceImplement;
    private final BookServiceImplement bookServiceImplement;
    private final BookStoreServiceImplement bookStoreServiceImplement;

    public BookController(AuthorServiceImplement authorServiceImplement, BookServiceImplement bookServiceImplement, BookStoreServiceImplement bookStoreServiceImplement) {
        this.authorServiceImplement = authorServiceImplement;
        this.bookServiceImplement = bookServiceImplement;
        this.bookStoreServiceImplement = bookStoreServiceImplement;
    }

    @GetMapping("")
    public String getBooksPage(HttpServletRequest request, @RequestParam(required = false) String error, Model model)
    {
        if(error != null)
        {
            model.addAttribute("errormessage", error);
        }
        model.addAttribute("Books", bookServiceImplement.listBooks());
        return "listBooks";
    }

    @GetMapping("/add")
    public String getAddBookPage(HttpServletRequest request, @RequestParam(required = false) String error, Model model)
    {
        model.addAttribute("stores", bookStoreServiceImplement.findAll());
        return "addBook";
    }

    @PostMapping("/add")
    public String postAddBook(HttpServletRequest request, @RequestParam(required = false) String error, Model model)
    {
        bookServiceImplement.saveBook(request.getParameter("ISBN").toString(), request.getParameter("Title").toString(), request.getParameter("Genre").toString(), Integer.parseInt(request.getParameter("Year").toString()), Long.parseLong(request.getParameter("Store").toString()));
        return "redirect:/books";
    }

    @GetMapping("/edit")
    public String getEditBookPage(HttpServletRequest request, @RequestParam(required = false) String error, Model model)
    {
        String errorMessage = "Error selected book is not valid";
        return "redirect:/books?error=" + errorMessage;
    }

    @GetMapping("/edit/{id}")
    public String getEditBookPage(HttpServletRequest request, @RequestParam(required = false) String error, Model model, @PathVariable String id)
    {
        Book b = bookServiceImplement.findBookById(Long.parseLong(id));
        if(b == null)
        {
            return "redirect:/books/edit";
        }
        model.addAttribute("Book", b);
        model.addAttribute("stores", bookStoreServiceImplement.findAll());
        return "editBook";
    }

    @PostMapping("/edit/{id}")
    public String postAddBook(HttpServletRequest request, @RequestParam(required = false) String error, Model model, @PathVariable String id)
    {
        bookServiceImplement.editBook(request.getParameter("ISBN").toString(), request.getParameter("Title").toString(), request.getParameter("Genre").toString(), Integer.parseInt(request.getParameter("Year").toString()), Long.parseLong(request.getParameter("Store").toString()), Long.parseLong(id));
        return "redirect:/books";
    }

    @GetMapping("/delete/{id}")
    public String getDeleteBookPage(HttpServletRequest request, @RequestParam(required = false) String error, Model model, @PathVariable String id)
    {
        model.addAttribute("Book", bookServiceImplement.findBookById(Long.parseLong(id)));
        return "deleteBook";
    }

    @PostMapping("/delete/{id}")
    public String postDeleteBook(HttpServletRequest request, @RequestParam(required = false) String error, Model model, @PathVariable String id)
    {
        bookServiceImplement.deleteBook(Long.parseLong(id));
        return "redirect:/books";
    }
}
