package mk.ukim.finki.wp.lab.repository.jpa;

import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReviewJpaRepository extends JpaRepository<Review, Long> {
    List<Review> findReviewsByBook(Book book);

    List<Review> findReviewsByBookAndTimestampAfter(Book book, LocalDateTime from);
    List<Review> findReviewsByBookAndTimestampBefore(Book book, LocalDateTime to);
    List<Review> findReviewsByBookAndTimestampBetween(Book book, LocalDateTime from, LocalDateTime to);
    void deleteAllByBookId(Long id);
}
