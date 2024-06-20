package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.BookStore;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
   public static List<Author> authors = null;
   public static List<Book> books = null;
   public static List<BookStore> bookStores = null;
//
//    @PostConstruct
//    public void init(){
//        authors = new ArrayList<>();
//        books = new ArrayList<>();
//        bookStores = new ArrayList<>();
//        authors.add(new Author(1L,"Paula","Munier","The author that wrote Home at Night"));
//        authors.add(new Author(2L,"John","Green","The author that wrote Let it Snow"));
//        authors.add(new Author(3L,"Maureen","Johnson","The author that wrote Let it Snow"));
//        authors.add(new Author(4L,"Joanne","Rowling","The author that wrote Harry Potter"));
//        authors.add(new Author(5L,"Lewis","Carroll","The author that wrote Alice's Adventures in Wonderland"));
//
//        bookStores.add(new BookStore("Akademska Kniga","Skopje","Bulevar Partizanski Odredi 23"));
//        bookStores.add(new BookStore("Literatura.mk","Skopje","Makedonija 15"));
//        bookStores.add(new BookStore("Analog","Skopje","Dimitrie Cupovski 25"));
//        bookStores.add(new BookStore("Nobel Bookstore","Skopje","Kazandziska 14"));
//        bookStores.add(new BookStore("Ikona Bookstore","Skopje","Sv. Kiril i Metodija 23"));
//
//        books.add(new Book("HT", "Home at Night", "Mystery",2023,List.of(authors.get(0)),bookStores.get(0)));
//        books.add(new Book("LS", "Let it Snow", "Christmas",2008,List.of(authors.get(1),authors.get(2)),bookStores.get(1)));
//        books.add(new Book("HPSS", "Harry Potter and the Sorcerer's Stone","Fiction",2001,List.of(authors.get(3)),bookStores.get(2)));
//        books.add(new Book("AAW", "Alice's Adventures in Wonderland","Fantasy",1865,List.of(authors.get(4)),bookStores.get(3)));
//        books.add(new Book("PW", "Peter and Wendy","Novel",1904,List.of(new Author(6L, "James Matthew","Barrie","The author that wrote Peter And Wendy")),bookStores.get(4)));
//
//    }
}
