package mk.ukim.finki.wp.lab.service;

import ch.qos.logback.core.pattern.parser.OptionTokenizer;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.Review;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ReviewService {
    List<Review> findAll();
    Optional<Review> findById(Long id);
    List<Review> findAllByBook(Book book);
    Optional<Review> save(Integer score, String description, Long bookId, LocalDateTime timestamp);
    Optional<Review> update(Long id, Integer score, String description, Long bookId, LocalDateTime timestamp);
    void delete(Long id);
    List<Review> filterReviews(Book book, LocalDateTime from, LocalDateTime to);

    void deleteAllByBookId(Long id);
}
