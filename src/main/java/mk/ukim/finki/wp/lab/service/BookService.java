package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> listBooks();
    Book findBookById(Long id);
    Author addAuthorToBook(Long authorId, Long id);
    List<Book> filterBooks(String title, int year);
    Optional<Book> save(String isbn, String title, String genre, int year, Long bookStoreId, Long bookPriceId);
    Optional<Book> update(Long id,String isbn, String title, String genre, int year, Long bookStoreId, Long bookPriceId);
    void delete(Long id);
}
