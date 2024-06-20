package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Author;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<Author> listAuthors();
    Optional<Author> findById(Long id);
    Optional<Author> save(String name, String surname, String biography, LocalDate dateOfBirth);
    Optional<Author> update(Long id, String name, String surname, String biography, LocalDate dateOfBirth);
}
