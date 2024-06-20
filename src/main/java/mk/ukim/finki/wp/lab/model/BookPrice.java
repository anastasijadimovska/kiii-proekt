package mk.ukim.finki.wp.lab.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
public class BookPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    int price;
    float discount;



    public BookPrice(int price, float discount) {
        this.price = price;
        this.discount = discount;
    }

    public BookPrice() {

    }

    public void setId(Long id) {
        this.id = id;
    }

}
