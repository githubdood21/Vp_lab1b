package mk.finki.ukim.mk.lab.Web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.finki.ukim.mk.lab.Service.BookService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name="home", urlPatterns = "/listBooks")
public class BookListServlet extends HttpServlet {

    private final BookService bookService;
    private final SpringTemplateEngine springTemplateEngine;
    public BookListServlet(SpringTemplateEngine springTemplateEngine, BookService bookService)
    {
        this.springTemplateEngine = springTemplateEngine;
        this.bookService = bookService;
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange exchange = JakartaServletWebApplication.buildApplication(getServletContext()).buildExchange(req,resp);
        WebContext context = new WebContext(exchange);

        context.setVariable("test", 1);
        context.setVariable("Books", bookService.listBooks());

        this.springTemplateEngine.process("listBooks.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
