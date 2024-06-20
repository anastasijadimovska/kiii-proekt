package mk.ukim.finki.wp.lab.repository.jpa;

import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookJpaRepository extends JpaRepository<Book,Long> {
    List<Book> findAllByTitleAndYearLessThan (String title, int year);
    List<Book> findAllByTitle (String title);
    List<Book> findAllByYearLessThan (int year);

}
