package mk.finki.ukim.mk.lab.Web;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.finki.ukim.mk.lab.Model.Book;
import mk.finki.ukim.mk.lab.Service.AuthorService;
import mk.finki.ukim.mk.lab.Service.BookService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.ISpringTemplateEngine;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name="author", urlPatterns = "/author")
public class AuthorServlet extends HttpServlet {

    private final BookService bookService;
    private final AuthorService authorService;
    private final SpringTemplateEngine springTemplateEngine;
    public AuthorServlet(SpringTemplateEngine springTemplateEngine, BookService bookService, AuthorService authorService)
    {
        this.springTemplateEngine = springTemplateEngine;
        this.bookService = bookService;
        this.authorService = authorService;
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange exchange = JakartaServletWebApplication.buildApplication(getServletContext()).buildExchange(req, resp);
        WebContext context = new WebContext(exchange);
        String isbn = req.getParameter("BookSelect");

        context.setVariable("Authors", authorService.listAuthors());
        context.setVariable("Book", bookService.findBookByIsbn(isbn));

        this.springTemplateEngine.process("authorList.html", context, resp.getWriter());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
