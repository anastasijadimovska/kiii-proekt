package mk.ukim.finki.wp.lab.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Data
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
     Integer score;
     @Column(length = 4000)
     String description;
     @ManyToOne(cascade  = CascadeType.PERSIST,fetch = FetchType.LAZY)
     Book book;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
     LocalDateTime timestamp;

    public Review(Long id, Integer score, String description, Book book, LocalDateTime timestamp) {
        this.id = id;
        this.score = score;
        this.description = description;
        this.book = book;
        this.timestamp = timestamp;
    }
    public Review() {
    }

    public Review(Integer score, String description, Book book, LocalDateTime timestamp) {
        this.score = score;
        this.description = description;
        this.book = book;
        this.timestamp = timestamp;
    }
}
