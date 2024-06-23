package mk.ukim.finki.wp.lab.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Data
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Convert(converter = AuthorFullnameConverter.class)
    AuthorFullname fullName;
    @Column(length = 4000)
    String biography;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    LocalDate dateOfBirth;

    public Author(Long id, String name, String surname, String biography,LocalDate dateOfBirth) {
        this.id = id;
        this.fullName = new AuthorFullname(name,surname);
        this.biography = biography;
        this.dateOfBirth = dateOfBirth;
    }
    public Author( String name, String surname, String biography,LocalDate dateOfBirth) {
        this.id = (long) (Math.random() * 1000);
        this.fullName = new AuthorFullname(name,surname);
        this.biography = biography;
        this.dateOfBirth = dateOfBirth;
    }
    public Author() {

    }
}
