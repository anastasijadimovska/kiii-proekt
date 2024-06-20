package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.BookStore;

import java.util.List;
import java.util.Optional;

public interface BookStoreService {
    public List<BookStore> findAll();
    public Optional<BookStore> findById(Long id);
    public void delete (Long id);
    public Optional<BookStore> save(String name, String city, String address, int copies);
    public Optional<BookStore> update(Long id,String name, String city, String address, int copies);
}
