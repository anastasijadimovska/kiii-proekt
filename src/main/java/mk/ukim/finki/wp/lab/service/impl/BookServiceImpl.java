package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.exceptions.InvalidAuthorException;
import mk.ukim.finki.wp.lab.exceptions.InvalidBookIdException;
import mk.ukim.finki.wp.lab.exceptions.InvalidBookStoreException;
import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.BookPrice;
import mk.ukim.finki.wp.lab.model.BookStore;
import mk.ukim.finki.wp.lab.repository.jpa.*;
import mk.ukim.finki.wp.lab.service.BookService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final BookJpaRepository bookRepository;
    private final AuthorJpaRepository authorRepository;
    private final BookStoreJpaRepository bookStoreRepository;
    private final BookPriceJpaRepository priceJpaRepository;


    public BookServiceImpl(BookJpaRepository bookRepository, AuthorJpaRepository authorRepository, BookStoreJpaRepository bookStoreRepository, BookPriceJpaRepository priceJpaRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.bookStoreRepository = bookStoreRepository;
        this.priceJpaRepository = priceJpaRepository;
    }

    @Override
    public List<Book> listBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book findBookById(Long id) {
        if(id==null){
            throw new InvalidBookIdException();
        }
        return bookRepository.findById(id).get();
    }

    @Override
        public Author addAuthorToBook(Long authorId, Long id) {
        if(authorId==null || authorId==0 || id==null){
            throw new InvalidAuthorException();
        }
            Author author = authorRepository.findById(authorId).get();
            Book book = bookRepository.findById(id).orElseThrow(InvalidBookIdException::new);

            List<Author> authors = new ArrayList<>(book.getAuthors());
            if(!authors.contains(author)) {
                authors.add(author);
                book.setAuthors(authors);
                bookRepository.save(book);
            }


            return author;
    }
    @Override
    public List<Book> filterBooks(String title, int year) {
        List<Book> result = null;
        Integer year1 = year;
        if(title!=null && year1!=0)
        {
            result = bookRepository.findAllByTitleAndYearLessThan(title, year);
        }
        else if(title!=null)
        {
            result = bookRepository.findAllByTitle(title);
        }else if(year1!=0)
        {
            result = bookRepository.findAllByYearLessThan(year);
        }
        else result = bookRepository.findAll();

        return result;
    }

    @Override
    public Optional<Book> save(String isbn, String title, String genre, int year, Long bookStoreId, Long bookPriceId) {
       BookStore bookStore = bookStoreRepository.findById(bookStoreId).orElseThrow(InvalidBookStoreException::new);
        BookPrice bookPrice = priceJpaRepository.findById(bookPriceId).get();
        Book book = new Book(isbn, title, genre, year, bookStore, bookPrice);
        return Optional.of(bookRepository.save(book));
    }

    @Override
    public Optional<Book> update(Long id, String isbn, String title, String genre, int year, Long bookStoreId, Long bookPriceId) {
        BookStore bookStore = bookStoreRepository.findById(bookStoreId).orElseThrow(InvalidBookStoreException::new);
        Book updatedBook =  bookRepository.findById(id).orElseThrow(InvalidBookIdException::new);
        BookPrice bookPrice = priceJpaRepository.findById(bookPriceId).get();
        bookRepository.deleteById(id);
        updatedBook.setIsbn(isbn);
        updatedBook.setTitle(title);
        updatedBook.setGenre(genre);
        updatedBook.setBookStore(bookStore);
        updatedBook.setYear(year);
        updatedBook.setBookPrice(bookPrice);
        return Optional.of(bookRepository.save(updatedBook));
    }

    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);

    }

}







