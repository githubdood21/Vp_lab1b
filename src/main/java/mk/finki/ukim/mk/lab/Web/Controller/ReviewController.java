package mk.finki.ukim.mk.lab.Web.Controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.finki.ukim.mk.lab.Model.Book;
import mk.finki.ukim.mk.lab.Model.Review;
import mk.finki.ukim.mk.lab.Service.Implement.BookServiceImplement;
import mk.finki.ukim.mk.lab.Service.Implement.ReviewServiceImplement;
import mk.finki.ukim.mk.lab.Service.ReviewService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/review")
public class ReviewController {
    private final BookServiceImplement bookServiceImplement;
    private final ReviewServiceImplement reviewServiceImplement;

    public ReviewController(BookServiceImplement bookServiceImplement, ReviewServiceImplement reviewServiceImplement) {
        this.bookServiceImplement = bookServiceImplement;
        this.reviewServiceImplement = reviewServiceImplement;
    }

    @GetMapping("/{id}")
    public String getReviewPage(HttpServletRequest request, @RequestParam(required = false) String error, Model model, @PathVariable String id)
    {
        model.addAttribute("Review", reviewServiceImplement.findReviewById(Long.parseLong(id)));
        return "bookreview";
    }
    //do crud stopped at 15.3
    @GetMapping("/add/{id}")
    public String getAddReviewPage(HttpServletRequest request, @RequestParam(required = false) String error, Model model, @PathVariable String id)
    {
        model.addAttribute("Book", bookServiceImplement.findBookById(Long.parseLong(id)));
        return "addreview";
    }

    @PostMapping("/add/{id}")
    public String postAddReview(HttpServletRequest request, @RequestParam(required = false) String error, Model model, @PathVariable String id)
    {
        Book b = bookServiceImplement.findBookById(Long.parseLong(id));
        Review r = reviewServiceImplement.saveReview(Integer.parseInt(request.getParameter("Score").toString()), request.getParameter("Description").toString(), LocalDateTime.parse(request.getParameter("Date").toString()));
        bookServiceImplement.addReviewToBook(r.getId(), b.getId());
        return "redirect:/books";
    }

    @GetMapping("/edit")
    public String getEditReviewPage(HttpServletRequest request, @RequestParam(required = false) String error, Model model)
    {
        String errorMessage = "Error selected review is not valid";
        return "redirect:/books?error=" + errorMessage;
    }

    @GetMapping("/edit/{id}")
    public String getEditReviewPage(HttpServletRequest request, @RequestParam(required = false) String error, Model model, @PathVariable String id)
    {
        Review r = reviewServiceImplement.findReviewById(Long.parseLong(id));
        if(r == null)
        {
            return "redirect:/review/edit";
        }
        model.addAttribute("Review", r);
        return "editreview";
    }

    @PostMapping("/edit/{id}")
    public String postEditReview(HttpServletRequest request, @RequestParam(required = false) String error, Model model, @PathVariable String id)
    {
        reviewServiceImplement.editReview(Integer.parseInt(request.getParameter("Score").toString()), request.getParameter("Description").toString(), LocalDateTime.parse(request.getParameter("Date").toString()), Long.parseLong(id));
        return "redirect:/books";
    }
    @GetMapping("/delete")
    public String getDeleteReviewPage(HttpServletRequest request, @RequestParam(required = false) String error, Model model)
    {
        String errorMessage = "Error selected review is not valid";
        return "redirect:/books?error=" + errorMessage;
    }
    @GetMapping("/delete/{id}")
    public String getDeleteReviewPage(HttpServletRequest request, @RequestParam(required = false) String error, Model model, @PathVariable String id)
    {
        model.addAttribute("Review", reviewServiceImplement.findReviewById(Long.parseLong(id)));
        return "deletereview";
    }

    @PostMapping("/delete/{id}")
    public String postDeleteReview(HttpServletRequest request, @RequestParam(required = false) String error, Model model, @PathVariable String id)
    {
        reviewServiceImplement.deleteReview(Long.parseLong(id));
        return "redirect:/books";
    }
}
