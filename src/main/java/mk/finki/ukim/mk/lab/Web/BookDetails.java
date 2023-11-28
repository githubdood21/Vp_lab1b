package mk.finki.ukim.mk.lab.Web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.finki.ukim.mk.lab.Model.Author;
import mk.finki.ukim.mk.lab.Model.Book;
import mk.finki.ukim.mk.lab.Service.AuthorService;
import mk.finki.ukim.mk.lab.Service.BookService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name="BookDetails", urlPatterns = "/bookdetails")
public class BookDetails extends HttpServlet {
    private final BookService bookService;
    private final AuthorService authorService;
    private final SpringTemplateEngine springTemplateEngine;
    public BookDetails(SpringTemplateEngine springTemplateEngine, BookService bookService, AuthorService authorService)
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
        String authorId = req.getParameter("AuthorSelect");

        Book book = bookService.findBookByIsbn(isbn);
        Author author = authorService.findById(Long.parseLong(authorId));
        book.addAuthor(author);

        context.setVariable("Book", book);

        this.springTemplateEngine.process("bookDetailsServlet.html", context, resp.getWriter());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
