package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.exceptions.InvalidBookStoreException;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.BookStore;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BookStoreRepository {
    public List<BookStore> findAll(){
        return DataHolder.bookStores;
    }
    public Optional<BookStore> findById(Long id){
        return DataHolder.bookStores.stream().filter(b -> b.getId().equals(id)).findFirst();
    }
    public void delete (Long id){
        DataHolder.bookStores.removeIf(b -> b.getId().equals(id));
    }
    public Optional<BookStore> save(BookStore bookStore){
        DataHolder.bookStores.removeIf(b -> b.getId().equals(bookStore.getId()));
        DataHolder.bookStores.add(bookStore);
        return Optional.of(bookStore);
    }
    public Optional<BookStore> update(BookStore bookStore){
        BookStore updatedBookStore = DataHolder.bookStores.stream().filter(b -> b.getId().equals(bookStore.getId())).findFirst().orElseThrow(InvalidBookStoreException::new);
        updatedBookStore.setAddress(bookStore.getAddress());
        updatedBookStore.setCity(bookStore.getCity());
        updatedBookStore.setName(bookStore.getName());
        return Optional.of(updatedBookStore);
    }
}
