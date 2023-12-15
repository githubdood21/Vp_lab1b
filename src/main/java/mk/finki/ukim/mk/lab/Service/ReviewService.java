package mk.finki.ukim.mk.lab.Service;

import mk.finki.ukim.mk.lab.Model.Author;
import mk.finki.ukim.mk.lab.Model.Book;
import mk.finki.ukim.mk.lab.Model.Review;

import java.time.LocalDateTime;
import java.util.List;

public interface ReviewService {
    List<Review> listReviews();
    Review findReviewById(Long id);
    Review saveReview(Integer score, String description, LocalDateTime timestamp);
    Review editReview(Integer score, String description, LocalDateTime timestamp, Long Id);
    public List<Review> filterformtodate(Long bookId, LocalDateTime from, LocalDateTime to);
    void deleteReview(Long Id);
}
