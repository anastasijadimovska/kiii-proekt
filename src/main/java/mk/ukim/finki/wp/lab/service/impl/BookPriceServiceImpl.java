package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.BookPrice;
import mk.ukim.finki.wp.lab.repository.jpa.BookPriceJpaRepository;
import mk.ukim.finki.wp.lab.service.BookPriceService;
import org.springframework.stereotype.Service;

@Service
public class BookPriceServiceImpl implements BookPriceService {
   private final BookPriceJpaRepository bookPriceJpaRepository;

    public BookPriceServiceImpl(BookPriceJpaRepository bookPriceJpaRepository) {
        this.bookPriceJpaRepository = bookPriceJpaRepository;
    }

    @Override
    public BookPrice save(int price, float discount) {
        return bookPriceJpaRepository.save(new BookPrice(price,discount));
    }

    @Override
    public BookPrice findByPrice(int price) {
        return bookPriceJpaRepository.findBookPriceByPrice(price);
    }

    @Override
    public BookPrice update(Long id, int price, float discount) {
        BookPrice bookPrice = bookPriceJpaRepository.findById(id).get();
        bookPrice.setPrice(price);
        bookPrice.setDiscount(discount);
        return bookPriceJpaRepository.save(bookPrice);
    }

}
