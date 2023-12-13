package mk.finki.ukim.mk.lab.Web.Controller;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.finki.ukim.mk.lab.Model.Author;
import mk.finki.ukim.mk.lab.Model.Book;
import mk.finki.ukim.mk.lab.Service.Implement.AuthorServiceImplement;
import mk.finki.ukim.mk.lab.Service.Implement.BookServiceImplement;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.time.LocalDate;

@Controller
@RequestMapping("/author")
public class AuthorController {

    private final BookServiceImplement bookServiceImplement;
    private final AuthorServiceImplement authorServiceImplement;

    public AuthorController(BookServiceImplement bookServiceImplement, AuthorServiceImplement authorServiceImplement) {
        this.bookServiceImplement = bookServiceImplement;
        this.authorServiceImplement = authorServiceImplement;
    }

    @PostMapping("")
    public String postAuthorList(HttpServletRequest request, @RequestParam(required = false) String error, Model model)
    {
        String isbn = request.getParameter("BookSelect");

        model.addAttribute("Authors", authorServiceImplement.listAuthors());
        model.addAttribute("Book", bookServiceImplement.findBookByIsbn(isbn));

        return "authorList";
    }

    @GetMapping("/add")
    public String getAddAuthorPage(HttpServletRequest request, @RequestParam(required = false) String error, Model model)
    {
        return "addAuthor";
    }

    @PostMapping("/add")
    public String postAuthorBook(HttpServletRequest request, @RequestParam(required = false) String error, Model model)
    {
        authorServiceImplement.saveAuthor(request.getParameter("Name").toString(), request.getParameter("Surname").toString(), request.getParameter("Biography").toString(), LocalDate.parse( request.getParameter("Date")));
        return "redirect:/books";
    }

    @GetMapping("/edit")
    public String getEditAuthorPage(HttpServletRequest request, @RequestParam(required = false) String error, Model model)
    {
        String errorMessage = "Error selected author is not valid";
        return "redirect:/books?error=" + errorMessage;
    }

    @GetMapping("/edit/{id}")
    public String getEditAuthorPage(HttpServletRequest request, @RequestParam(required = false) String error, Model model, @PathVariable String id)
    {
        Author a = authorServiceImplement.findById(Long.parseLong(id));
        if(a == null)
        {
            return "redirect:/author/edit";
        }
        model.addAttribute("Author", a);
        return "editAuthor";
    }

    @PostMapping("/edit/{id}")
    public String postEditAuthor(HttpServletRequest request, @RequestParam(required = false) String error, Model model, @PathVariable String id)
    {
        authorServiceImplement.editAuthor(request.getParameter("Name").toString(), request.getParameter("Surname").toString(), request.getParameter("Biography").toString(), LocalDate.parse( request.getParameter("Date")), Long.parseLong(id));
        return "redirect:/books";
    }
    @GetMapping("/delete")
    public String getDeleteAuthorPage(HttpServletRequest request, @RequestParam(required = false) String error, Model model)
    {
        String errorMessage = "Error selected author is not valid";
        return "redirect:/books?error=" + errorMessage;
    }
    @GetMapping("/delete/{id}")
    public String getDeleteAuthorPage(HttpServletRequest request, @RequestParam(required = false) String error, Model model, @PathVariable String id)
    {
        model.addAttribute("Author", authorServiceImplement.findById(Long.parseLong(id)));
        return "deleteAuthor";
    }

    @PostMapping("/delete/{id}")
    public String postDeleteAuthor(HttpServletRequest request, @RequestParam(required = false) String error, Model model, @PathVariable String id)
    {
        authorServiceImplement.deleteAuthor(Long.parseLong(id));
        return "redirect:/books";
    }
}
