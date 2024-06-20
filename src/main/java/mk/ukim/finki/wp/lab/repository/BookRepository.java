//package mk.ukim.finki.wp.lab.repository;
//
//import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
//import mk.ukim.finki.wp.lab.exceptions.InvalidBookIdException;
//import mk.ukim.finki.wp.lab.model.Author;
//import mk.ukim.finki.wp.lab.model.Book;
//import mk.ukim.finki.wp.lab.model.BookStore;
//import org.springframework.stereotype.Repository;
//
//import javax.xml.crypto.Data;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@Repository
//public class BookRepository {
//    public List<Book> findAll(){
//        return DataHolder.books;
//    }
//    public Optional<Book> findById(Long id){
//        return DataHolder.books.stream().filter(b -> b.getId().equals(id)).findFirst();
//    }
//
//    public Author addAuthorToBook(Author author, Book book){
//        Book book1 = DataHolder.books.stream().filter(b -> b.getIsbn().equals(book.getIsbn())).findFirst().orElseThrow(InvalidBookIdException::new);
//        List<Author> authors = new ArrayList<>(book1.getAuthors());
//            authors.add(author);
//        book1.setAuthors(authors);
//
//        return author;
//    }
//    public List<Book> filterBooks(String title, int year){
//        List<Book> result = null;
//        Integer year1 = year;
//        if(title!=null && year1!=0)
//        {
//            result = DataHolder.books.stream().filter(book -> book.getTitle().contains(title) && book.getYear() > year).collect(Collectors.toList());
//        }
//        else if(title!=null)
//        {
//            result = DataHolder.books.stream().filter(book -> book.getTitle().contains(title)).collect(Collectors.toList());
//        }else if(year1!=0)
//        {
//            result = DataHolder.books.stream().filter(book -> book.getYear() > year).collect(Collectors.toList());
//        }
//        else result = findAll();
//
//        return result;
//    }
//    public Optional<Book> saveBook (Book book){
//        DataHolder.books.removeIf(b -> b.getTitle().equals(book.getTitle()));
//        DataHolder.books.add(book);
//        return Optional.of(book);
//    }
//    public void delete(Long id){
//        DataHolder.books.removeIf(r -> r.getId().equals(id));
//    }
//    public Optional<Book> update(Book book){
//       Book updatedBook =  DataHolder.books.stream().filter(b -> b.getId().equals(book.getId())).findFirst().orElseThrow(InvalidBookIdException::new);
//        updatedBook.setIsbn(book.getIsbn());
//        updatedBook.setTitle(book.getTitle());
//        updatedBook.setGenre(book.getGenre());
//        updatedBook.setBookStore(book.getBookStore());
//        updatedBook.setYear(book.getYear());
//        return Optional.of(updatedBook);
//    }
//}
