package mk.ukim.finki.wp.lab.repository.jpa;

import mk.ukim.finki.wp.lab.model.BookPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookPriceJpaRepository extends JpaRepository<BookPrice, Long> {
    BookPrice findBookPriceByPrice(int price);
}
