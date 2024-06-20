package mk.ukim.finki.wp.lab.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.wp.lab.exceptions.InvalidBookIdException;
import mk.ukim.finki.wp.lab.exceptions.InvalidBookStoreException;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.BookStore;
import mk.ukim.finki.wp.lab.service.BookStoreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/bookStore")
public class bookStoreController {
    private final BookStoreService bookStoreService;

    public bookStoreController(BookStoreService bookStoreService) {
        this.bookStoreService = bookStoreService;
    }

    @GetMapping
    public String getBookStorePage(@RequestParam(required = false) String error, Model model){
        if(error!=null && !error.isEmpty()){
            model.addAttribute("hasError",true);
            model.addAttribute("error",error);
        }
        model.addAttribute("bookStores",bookStoreService.findAll());
        return "listBookStores";
    }

    @GetMapping("/add-form")
    public String getBookStoreDetails(Model model){
        model.addAttribute("bookStores",bookStoreService.findAll());
        return "add-bookstore";
    }

    @PostMapping("/add")
    public String saveBook(@RequestParam(required = false) Long bookStoreId,
                           @RequestParam String name,
                           @RequestParam String city,
                           @RequestParam String address,
                           @RequestParam int copies,
                           HttpServletRequest request){
        request.getSession().setAttribute("copies",copies);
        if(bookStoreId!=null){
            bookStoreService.update(bookStoreId,name,city,address, copies);

        }else bookStoreService.save(name,city,address, copies);
        return "redirect:/bookStore";
    }
    @GetMapping("/edit-form/{id}")
    public String getEditPage(@PathVariable Long id, Model model){
        try {

            model.addAttribute("bookStore", bookStoreService.findById(id).get());
            model.addAttribute("bookStores", bookStoreService.findAll());
            return "add-bookstore";

        }catch (RuntimeException exception){
            return "redirect:/bookStore?error="+exception.getMessage();
        }
    }
    @PostMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id){
        bookStoreService.delete(id);
        return "redirect:/bookStore";
    }

}
