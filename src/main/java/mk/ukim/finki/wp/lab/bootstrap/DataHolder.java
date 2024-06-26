package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.BookPrice;
import mk.ukim.finki.wp.lab.model.BookStore;
import mk.ukim.finki.wp.lab.repository.jpa.AuthorJpaRepository;
import mk.ukim.finki.wp.lab.repository.jpa.BookJpaRepository;
import mk.ukim.finki.wp.lab.repository.jpa.BookPriceJpaRepository;
import mk.ukim.finki.wp.lab.repository.jpa.BookStoreJpaRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
   private final AuthorJpaRepository authorJpaRepository;
   private final BookStoreJpaRepository bookStoreJpaRepository;
   private final BookJpaRepository bookJpaRepository;
   private final BookPriceJpaRepository bookPriceJpaRepository;

   public static List<Author> authors = new ArrayList<>();
   public static List<Book> books =  new ArrayList<>();
   public static List<BookStore> bookStores =  new ArrayList<>();
   public static List<BookPrice> bookPrices = new ArrayList<>();

   public DataHolder(AuthorJpaRepository authorJpaRepository, BookStoreJpaRepository bookStoreJpaRepository, BookJpaRepository bookJpaRepository, BookPriceJpaRepository bookPriceJpaRepository) {
      this.authorJpaRepository = authorJpaRepository;
      this.bookStoreJpaRepository = bookStoreJpaRepository;
      this.bookJpaRepository = bookJpaRepository;
      this.bookPriceJpaRepository = bookPriceJpaRepository;
   }

   @PostConstruct
    public void init(){
        authors.add(new Author("Paula","Munier","The author that wrote Home at Night", LocalDate.of(1975,10,15)));
        authors.add(new Author("John","Green","The author that wrote Let it Snow", LocalDate.of(1995,7,2)));
        authors.add(new Author("Maureen","Johnson","The author that wrote Let it Snow", LocalDate.of(1999,9,5)));
        authors.add(new Author("Joanne","Rowling","The author that wrote Harry Potter", LocalDate.of(1978,10,8)));
        authors.add(new Author("Lewis","Carroll","The author that wrote Alice's Adventures in Wonderland", LocalDate.of(1955,10,24)));
       authorJpaRepository.saveAll(authors);

       System.out.println(authors);
        bookStores.add(new BookStore("Akademska Kniga","Skopje","Bulevar Partizanski Odredi 23", 800));
        bookStores.add(new BookStore("Literatura.mk","Skopje","Makedonija 15",500));
        bookStores.add(new BookStore("Analog","Skopje","Dimitrie Cupovski 25",410));
        bookStores.add(new BookStore("Nobel Bookstore","Skopje","Kazandziska 14",145));
        bookStores.add(new BookStore("Ikona Bookstore","Skopje","Sv. Kiril i Metodija 23",658));
       System.out.println(bookStores);
       bookStoreJpaRepository.saveAll(bookStores);

       bookPrices = List.of(new BookPrice(542,20),new BookPrice(280,20),new BookPrice(420,20),new BookPrice(265,10),new BookPrice(350,15));
       bookPriceJpaRepository.saveAll(bookPrices);

       books.add(new Book("HaT", "Home at Night", "Mystery",2023,List.of(authors.get(0)),bookStores.get(0),bookPrices.get(0)));
        books.add(new Book("LS", "Let it Snow", "Christmas",2008,List.of(authors.get(1),authors.get(2)),bookStores.get(1),bookPrices.get(1)));
        books.add(new Book("HPSS", "Harry Potter and the Sorcerer's Stone","Fiction",2001,List.of(authors.get(3)),bookStores.get(2),bookPrices.get(2)));
        books.add(new Book("AAW", "Alice's Adventures in Wonderland","Fantasy",1865,List.of(authors.get(4)),bookStores.get(3),bookPrices.get(3)));
        books.add(new Book("PW", "Peter and Wendy","Novel",1904,List.of(authors.get(0)),bookStores.get(4),bookPrices.get(4)));
       System.out.println(books);
        bookJpaRepository.saveAll(books);
    }
}
