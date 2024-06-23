package mk.ukim.finki.wp.lab.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.service.AuthorService;
import mk.ukim.finki.wp.lab.service.BookService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/authors")
public class AuthorController {
    private final AuthorService authorService;
    private final BookService bookService;

    public AuthorController(AuthorService authorService, BookService bookService) {
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @GetMapping
    public String getAuthorsPage(Model model, HttpServletRequest req){
        model.addAttribute("authors",authorService.listAuthors());
        Long id = (Long) req.getSession().getAttribute("authorId");
        return "authorList";
    }

    @PostMapping
    public String addAuthorToBook(Model model,HttpServletRequest req){
        Long bookId = (Long) req.getSession().getAttribute("bookId");
        String reqId = req.getParameter("authorId");
        Long id = Long.parseLong(reqId);

        req.getSession().setAttribute("author",authorService.findById(id).get());
        Author author = (Author) req.getSession().getAttribute("author");
        bookService.addAuthorToBook(author.getId(),bookId);

      //  Book book = (Book) req.getSession().getAttribute("book1");
        List<Author> list = bookService.findBookById(bookId).getAuthors();
        req.getSession().setAttribute("authors",list);

        return "redirect:/bookDetails";
    }
    @GetMapping("/add-form")
    public String getBookDetails(Model model){
        return "add-author";
    }
    @PostMapping("/add")
    public String saveAuthor(@RequestParam(required = false) Long id,
                             @RequestParam String name,
                             @RequestParam String surname,
                             @RequestParam String biography,
                             @RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate dateOfBirth){
        if(id!=null){
            authorService.update(id,name,surname,biography,dateOfBirth);


        }else  authorService.save(name,surname,biography,dateOfBirth);
        return "redirect:/authors";
    }
}
