package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.exceptions.InvalidAuthorException;
import mk.ukim.finki.wp.lab.exceptions.InvalidBookIdException;
import mk.ukim.finki.wp.lab.exceptions.InvalidBookStoreException;
import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.AuthorFullname;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.BookStore;
import mk.ukim.finki.wp.lab.repository.jpa.AuthorJpaRepository;
import mk.ukim.finki.wp.lab.service.AuthorService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorJpaRepository authorRepository;

    public AuthorServiceImpl(AuthorJpaRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> listAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Optional<Author> findById(Long id) {
        return authorRepository.findById(id);
    }
    @Override
    public Optional<Author> save(String name, String surname, String biography, LocalDate dateOfBirth) {
        Author author= new Author(name,surname,biography,dateOfBirth);
        return Optional.of(authorRepository.save(author));
    }

    @Override
    public Optional<Author> update(Long id, String name, String surname, String biography, LocalDate dateOfBirth) {
        Author updatedAuthor =  authorRepository.findById(id).orElseThrow(InvalidAuthorException::new);
        authorRepository.deleteById(id);
        updatedAuthor.setFullName(new AuthorFullname(name, surname));
        updatedAuthor.setBiography(biography);
        updatedAuthor.setDateOfBirth(dateOfBirth);
        return Optional.of(authorRepository.save(updatedAuthor));
    }
}
