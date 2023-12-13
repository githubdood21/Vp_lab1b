package mk.finki.ukim.mk.lab.Web.Controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.finki.ukim.mk.lab.Model.Book;
import mk.finki.ukim.mk.lab.Model.BookStore;
import mk.finki.ukim.mk.lab.Service.Implement.AuthorServiceImplement;
import mk.finki.ukim.mk.lab.Service.Implement.BookServiceImplement;
import mk.finki.ukim.mk.lab.Service.Implement.BookStoreServiceImplement;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/bookstore")
public class BookStoreController {
    private final AuthorServiceImplement authorServiceImplement;
    private final BookServiceImplement bookServiceImplement;
    private final BookStoreServiceImplement bookStoreServiceImplement;

    public BookStoreController(AuthorServiceImplement authorServiceImplement, BookServiceImplement bookServiceImplement, BookStoreServiceImplement bookStoreServiceImplement) {
        this.authorServiceImplement = authorServiceImplement;
        this.bookServiceImplement = bookServiceImplement;
        this.bookStoreServiceImplement = bookStoreServiceImplement;
    }

    @GetMapping("")
    public String getBookStorePage(HttpServletRequest request, @RequestParam(required = false) String error, Model model)
    {
        if(error != null)
        {
            model.addAttribute("errormessage", error);
        }
        model.addAttribute("BookStore", bookStoreServiceImplement.listBookStore());
        return "listBookStore";
    }

    @GetMapping("/add")
    public String getAddBookStorePage(HttpServletRequest request, @RequestParam(required = false) String error, Model model)
    {
        return "addBookStore";
    }

    @PostMapping("/add")
    public String postAddBookStore(HttpServletRequest request, @RequestParam(required = false) String error, Model model)
    {
        bookStoreServiceImplement.saveBookStore(request.getParameter("Name").toString(), request.getParameter("City").toString(), request.getParameter("Address").toString());
        return "redirect:/bookstore";
    }

    @GetMapping("/edit")
    public String getEditBookPageStore(HttpServletRequest request, @RequestParam(required = false) String error, Model model)
    {
        String errorMessage = "Error selected book store is not valid";
        return "redirect:/bookstore?error=" + errorMessage;
    }

    @GetMapping("/edit/{id}")
    public String getEditBookStorePage(HttpServletRequest request, @RequestParam(required = false) String error, Model model, @PathVariable String id)
    {
        BookStore bs = bookStoreServiceImplement.findById(Long.parseLong(id));
        if(bs == null)
        {
            return "redirect:/bookstore/edit";
        }
        model.addAttribute("BookStore", bs);
        return "editBookStore";
    }

    @PostMapping("/edit/{id}")
    public String postEditBook(HttpServletRequest request, @RequestParam(required = false) String error, Model model, @PathVariable String id)
    {
        bookStoreServiceImplement.editBookStore(request.getParameter("Name").toString(), request.getParameter("City").toString(), request.getParameter("Address").toString(), Long.parseLong(id));
        return "redirect:/bookstore";
    }

    @GetMapping("/delete")
    public String getDeleteBookStorePage(HttpServletRequest request, @RequestParam(required = false) String error, Model model)
    {
        String errorMessage = "Error selected book store is not valid";
        return "redirect:/bookstore?error=" + errorMessage;
    }

    @GetMapping("/delete/{id}")
    public String getDeleteBookStorePage(HttpServletRequest request, @RequestParam(required = false) String error, Model model, @PathVariable String id)
    {
        model.addAttribute("BookStore", bookStoreServiceImplement.findById(Long.parseLong(id)));
        return "deleteBookStore";
    }

    @PostMapping("/delete/{id}")
    public String postDeleteBookStore(HttpServletRequest request, @RequestParam(required = false) String error, Model model, @PathVariable String id)
    {
        bookStoreServiceImplement.deleteBookStore(Long.parseLong(id));
        return "redirect:/bookstore";
    }

    @GetMapping("/addbook/{id}")
    public String getAddBookPage(HttpServletRequest request, @RequestParam(required = false) String error, Model model, @PathVariable String id)
    {
        BookStore bs = bookStoreServiceImplement.findById(Long.parseLong(id));
        if(bs == null)
        {
            return "redirect:/bookstore/edit";
        }
        model.addAttribute("Books", bookServiceImplement.listBooks());
        model.addAttribute("BookStore", bs);
        return "addBookToStore";
    }

    @PostMapping("/addbook/{id}")
    public String postAddBookPage(HttpServletRequest request, @RequestParam(required = false) String error, Model model, @PathVariable String id)
    {
        bookStoreServiceImplement.addBookToStore(Long.parseLong(id), Long.parseLong(request.getParameter("Book")));
        return "redirect:/bookstore";
    }
}
