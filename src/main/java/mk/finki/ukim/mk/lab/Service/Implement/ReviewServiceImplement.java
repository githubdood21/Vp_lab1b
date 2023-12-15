package mk.finki.ukim.mk.lab.Service.Implement;

import mk.finki.ukim.mk.lab.Model.Author;
import mk.finki.ukim.mk.lab.Model.Book;
import mk.finki.ukim.mk.lab.Model.BookStore;
import mk.finki.ukim.mk.lab.Model.Review;
import mk.finki.ukim.mk.lab.Repository.jpa.BookRepository;
import mk.finki.ukim.mk.lab.Repository.jpa.ReviewRepository;
import mk.finki.ukim.mk.lab.Service.ReviewService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImplement implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final BookRepository bookRepository;

    public ReviewServiceImplement(ReviewRepository reviewRepository, BookRepository bookRepository) {
        this.reviewRepository = reviewRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Review> listReviews() {
        return reviewRepository.findAll();
    }

    @Override
    public Review findReviewById(Long id) {
        Optional<Review> r = reviewRepository.findById(id);
        if(r.isEmpty())
        {
            return null;
        }
        return r.get();
    }

    @Override
    public Review saveReview(Integer score, String description, LocalDateTime timestamp) {
        if(score == null || description.isEmpty() || timestamp == null)
        {
            //throw error ama me mrzi
            return null;
        }
        Review r = new Review(score, description, timestamp);
        reviewRepository.save(r);
        return r;
    }

    @Override
    public Review editReview(Integer score, String description, LocalDateTime timestamp, Long Id) {
        if(Id == null)
        {
            //throw error ama me mrzi
            return null;
        }
        Optional<Review> origin = reviewRepository.findById(Id);
        if(origin.isEmpty() || score == null || description.isEmpty() || timestamp == null)
        {
            //throw error ama me mrzi
            return null;
        }

        origin.get().setDescription(description);
        origin.get().setScore(score);
        origin.get().setTimestamp(timestamp);

        reviewRepository.flush();

        return origin.get();
    }

    public List<Review> filterformtodate(Long bookId, LocalDateTime from, LocalDateTime to)
    {
        if(bookId == null || from == null || to == null)
        {
            return null;
        }
        Optional<Book> book = bookRepository.findById(bookId);
        if(book.isEmpty())
        {
            return null;
        }

        return book.get().getReviews().stream().filter(x -> x.getTimestamp().isBefore(to)).filter(x -> x.getTimestamp().isAfter(from)).collect(Collectors.toList());

    }

    @Override
    public void deleteReview(Long Id) {
        if(Id == null)
        {
            //throw error
            return;
        }
        Optional<Review> r = reviewRepository.findById(Id);
        if(r.isEmpty())
        {
            //throw error
            return;
        }
        r.get().getBook().getReviews().removeIf(x -> x.getId().equals(r.get().getId()));
        reviewRepository.delete(r.get());
    }
}
