package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.exceptions.InvalidBookIdException;
import mk.ukim.finki.wp.lab.exceptions.InvalidReviewException;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.Review;
import mk.ukim.finki.wp.lab.repository.jpa.BookJpaRepository;
import mk.ukim.finki.wp.lab.repository.jpa.ReviewJpaRepository;
import mk.ukim.finki.wp.lab.service.ReviewService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewJpaRepository reviewJpaRepository;
    private final BookJpaRepository bookJpaRepository;

    public ReviewServiceImpl(ReviewJpaRepository reviewJpaRepository, BookJpaRepository bookJpaRepository) {
        this.reviewJpaRepository = reviewJpaRepository;
        this.bookJpaRepository = bookJpaRepository;
    }

    @Override
    public List<Review> findAll() {
        return reviewJpaRepository.findAll();
    }

    @Override
    public Optional<Review> findById(Long id) {
        return reviewJpaRepository.findById(id);
    }

    @Override
    public List<Review> findAllByBook(Book book) {

        return reviewJpaRepository.findReviewsByBook(book);
    }

    @Override
    public Optional<Review> save(Integer score, String description, Long bookId, LocalDateTime timestamp) {
       Book book = bookJpaRepository.findById(bookId).orElseThrow(InvalidBookIdException::new);
       Review review = new Review(score,description,book,timestamp);
        return Optional.of(reviewJpaRepository.save(review));
    }

    @Override
    public Optional<Review> update(Long id, Integer score, String description, Long bookId, LocalDateTime timestamp) {
        Book book = bookJpaRepository.findById(bookId).orElseThrow(InvalidBookIdException::new);
        Review updatedReview = reviewJpaRepository.findById(id).orElseThrow(InvalidReviewException::new);
        reviewJpaRepository.deleteById(id);
        updatedReview.setDescription(description);
        updatedReview.setScore(score);
        updatedReview.setBook(book);
        updatedReview.setTimestamp(timestamp);

        return Optional.of(reviewJpaRepository.save(updatedReview));
    }

    @Override
    public void delete(Long id) {
        reviewJpaRepository.deleteById(id);
    }

    @Override
    public List<Review> filterReviews(Book book, LocalDateTime from, LocalDateTime to) {
        List<Review> result = reviewJpaRepository.findReviewsByBook(book);
        if(from!=null && to!=null && book!=null){
           result =  reviewJpaRepository.findReviewsByBookAndTimestampBetween(book,from,to);
        }else if(from!=null){
           result = reviewJpaRepository.findReviewsByBookAndTimestampBefore(book, to);
        }else if(to!=null){
           result = reviewJpaRepository.findReviewsByBookAndTimestampAfter(book, from);
        }

        return result;
    }

    @Override
    public void deleteAllByBookId(Long id){
        reviewJpaRepository.deleteAllByBookId(id);
    }

}
