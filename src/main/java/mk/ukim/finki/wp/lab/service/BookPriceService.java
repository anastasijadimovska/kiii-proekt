package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.BookPrice;

public interface BookPriceService{
    public BookPrice save (int price, float discount);
    BookPrice findByPrice(int price);
    BookPrice update(Long id, int price, float discount);
}
