package mk.ukim.finki.wp.lab.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String isbn;
    String title;
    String genre;
    int year;
    @ManyToMany(fetch = FetchType.EAGER)
    List<Author> authors;
    @ManyToOne
    BookStore bookStore;
    @ManyToOne
    BookPrice bookPrice;


    public Book(String isbn, String title, String genre, int year, List<Author> authors, BookStore bookStore,BookPrice bookPrice) {
        this.id = (long) (Math.random() * 1000);
        this.isbn = isbn;
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.authors = authors;
        this.bookStore = bookStore;
        this.bookPrice=bookPrice;
    }
    public Book(String isbn, String title, String genre, int year, BookStore bookStore,BookPrice bookPrice) {
        this.id = (long) (Math.random() * 1000);
        this.isbn = isbn;
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.bookStore = bookStore;
        this.bookPrice=bookPrice;
    }

    public Book(Long id, String isbn, String title, String genre, int year, BookStore bookStore,BookPrice bookPrice) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.bookStore = bookStore;
        this.bookPrice=bookPrice;
    }
    public Book() {

    }
}
