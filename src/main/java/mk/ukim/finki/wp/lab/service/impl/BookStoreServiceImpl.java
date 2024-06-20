package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.exceptions.InvalidBookStoreException;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.BookStore;
import mk.ukim.finki.wp.lab.repository.BookStoreRepository;
import mk.ukim.finki.wp.lab.repository.jpa.BookJpaRepository;
import mk.ukim.finki.wp.lab.repository.jpa.BookStoreJpaRepository;
import mk.ukim.finki.wp.lab.service.BookStoreService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookStoreServiceImpl implements BookStoreService {

    private final BookStoreJpaRepository bookStoreRepository;

    public BookStoreServiceImpl(BookStoreJpaRepository bookStoreRepository) {
        this.bookStoreRepository = bookStoreRepository;
    }

    @Override
    public List<BookStore> findAll() {
        return bookStoreRepository.findAll();
    }

    @Override
    public Optional<BookStore> findById(Long id) {
        return bookStoreRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        bookStoreRepository.deleteById(id);
    }

    @Override
    public Optional<BookStore> save(String name, String city, String address, int copies) {
        BookStore bookStore = new BookStore(name, city,address,copies);
        return Optional.of(bookStoreRepository.save(bookStore));
    }

    @Override
    public Optional<BookStore> update(Long id,String name, String city, String address,int copies) {
        BookStore updatedBookStore = bookStoreRepository.findById(id).orElseThrow(InvalidBookStoreException::new);
        bookStoreRepository.deleteById(id);
        updatedBookStore.setAddress(address);
        updatedBookStore.setCity(city);
        updatedBookStore.setName(name);
        updatedBookStore.setNumberOfCopies(copies);
        return Optional.of(bookStoreRepository.save(updatedBookStore));
    }
}
