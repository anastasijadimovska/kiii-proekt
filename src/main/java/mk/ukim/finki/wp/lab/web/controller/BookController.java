package mk.ukim.finki.wp.lab.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.BookPrice;
import mk.ukim.finki.wp.lab.service.BookPriceService;
import mk.ukim.finki.wp.lab.service.BookService;
import mk.ukim.finki.wp.lab.service.BookStoreService;
import mk.ukim.finki.wp.lab.service.ReviewService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final BookStoreService bookStoreService;
    private final ReviewService reviewService;
    private final BookPriceService priceService;

    public BookController(BookService bookService, BookStoreService bookStoreService, ReviewService reviewService, BookPriceService priceService) {
        this.bookService = bookService;
        this.bookStoreService = bookStoreService;
        this.reviewService = reviewService;
        this.priceService = priceService;
    }

    @GetMapping
    public String getBooksPage(@RequestParam(required = false) String error, Model model){
        if(error!=null && !error.isEmpty()){
            model.addAttribute("hasError",true);
            model.addAttribute("error",error);
        }
        model.addAttribute("books",bookService.listBooks());
        return "listBooks";
    }
    @PostMapping
    public String submitBooks(Model model,@RequestParam Long bookId, HttpServletRequest req){
        Book book = bookService.findBookById(bookId);
        req.getSession().setAttribute("bookId",bookId);
        req.getSession().setAttribute("book1",book);
        return "redirect:/authors";
    }
    @GetMapping("/add-form")
    public String getBookDetails(Model model){
        model.addAttribute("bookStores",bookStoreService.findAll());
        return "add-book";
    }

    @PostMapping("/add")
    public String saveBook(@RequestParam(required = false) Long id,
                           @RequestParam String title,
                           @RequestParam String isbn,
                           @RequestParam String genre,
                           @RequestParam int year,
                           @RequestParam Long bookStoreId,
                           @RequestParam int price,
                           @RequestParam float discount){
        if(priceService.findByPrice(price) != null){
            priceService.update(priceService.findByPrice(price).getId(),price,discount);
        }else{
            priceService.save(price, discount);
        }
        if(id!=null){

            bookService.update(id,isbn,title,genre,year,bookStoreId,priceService.findByPrice(price).getId());

        }else bookService.save(isbn,title,genre,year,bookStoreId,priceService.findByPrice(price).getId());
        return "redirect:/books";
    }
    @GetMapping("/edit-form/{id}")
    public String getEditPage(@PathVariable Long id, Model model){
        try {

                model.addAttribute("book", bookService.findBookById(id));
                model.addAttribute("bookStores", bookStoreService.findAll());
                return "add-book";

        }catch (RuntimeException exception){
            return "redirect:/books?error="+exception.getMessage();
        }
    }
    @PostMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id){
        bookService.delete(id);
        return "redirect:/books";
    }

}





