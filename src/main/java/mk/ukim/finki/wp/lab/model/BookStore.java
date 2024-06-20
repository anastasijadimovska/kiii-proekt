package mk.ukim.finki.wp.lab.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class BookStore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String city;
    private String address;
    private int numberOfCopies;

    public BookStore(String name, String city, String address, int numberOfCopies) {
        this.id = (long) (Math.random() * 1000);
        this.name = name;
        this.city = city;
        this.address = address;
        this.numberOfCopies=numberOfCopies;
    }
    public BookStore(Long id,String name, String city, String address,int numberOfCopies) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.address = address;
        this.numberOfCopies=numberOfCopies;
    }

    public BookStore() {

    }
}
