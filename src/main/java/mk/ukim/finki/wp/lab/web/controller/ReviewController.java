package mk.ukim.finki.wp.lab.web.controller;


import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.Review;
import mk.ukim.finki.wp.lab.repository.jpa.BookJpaRepository;
import mk.ukim.finki.wp.lab.repository.jpa.ReviewJpaRepository;
import mk.ukim.finki.wp.lab.service.BookService;
import mk.ukim.finki.wp.lab.service.ReviewService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/reviews")
public class ReviewController {
    private final BookService bookService;
    private final ReviewService reviewService;

    public ReviewController(BookService bookService, ReviewService reviewService) {
        this.bookService = bookService;
        this.reviewService = reviewService;
    }

    @GetMapping
    public String getReviewsPage(Model model,
                                 @RequestParam(required = false) Long bookId,
                                 @RequestParam(required = false) LocalDateTime toInput,
                                 @RequestParam(required = false) LocalDateTime fromInput) {
        model.addAttribute("books", bookService.listBooks());
        if (bookId == null) {
            return "listReviews";
        }
        Book selectedBook = bookService.findBookById(bookId);
        List<Review> reviews;
        if (toInput == null && fromInput == null){
            reviews = reviewService.findAllByBook(selectedBook).stream().sorted(getTimestampComparator()).collect(Collectors.toList());
         } else{
              reviews = reviewService.filterReviews(selectedBook, fromInput, toInput );
        }
        model.addAttribute("selectedBook", selectedBook);
        model.addAttribute("reviews", reviews);
        return "listReviews";
    }

    @PostMapping
    public String showBookReview( @RequestParam Long bookId,
                                  @RequestParam(required = false) LocalDateTime toInput,
                                  @RequestParam(required = false) LocalDateTime fromInput){
        if(fromInput!=null && toInput!=null){
            return "redirect:/reviews?bookId=" + bookId + "&fromInput="+fromInput + "&toInput="+toInput;
        }else if(fromInput!=null){
            toInput= LocalDateTime.now();
            return "redirect:/reviews?bookId=" + bookId + "&fromInput="+fromInput + "&toInput="+toInput;
        }else if(toInput!=null){
            fromInput = LocalDateTime.parse("1000-01-01T00:00:00");
            return "redirect:/reviews?bookId=" + bookId + "&fromInput="+fromInput + "&toInput="+toInput;
        }else return "redirect:/reviews?bookId=" + bookId;
    }

    @GetMapping("/add-form")
    public String getReviewDetails(Model model){
        model.addAttribute("books",bookService.listBooks());
        return "add-review";
    }

    @PostMapping("/add")
    public String saveBook(@RequestParam(required = false) Long id,
                           @RequestParam Integer score,
                           @RequestParam String description,
                           @RequestParam Long bookId,
                           @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime timestamp){
        if(id!=null){
            reviewService.update(id, score, description, bookId, timestamp);
        }else reviewService.save(score, description, bookId, timestamp);

        return "redirect:/reviews?bookId="+bookId;
    }
    @GetMapping("/edit-form/{id}")
    public String getEditPage(@PathVariable Long id, Model model){
        try {

            model.addAttribute("review", reviewService.findById(id).get());
            model.addAttribute("books", bookService.listBooks());
            return "add-review";

        }catch (RuntimeException exception){
            return "redirect:/reviews";
        }
    }
    @PostMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id){
       reviewService.delete(id);

        return "redirect:/reviews";
    }
    public static Comparator<Review> getTimestampComparator() {
        return Comparator.comparing(Review::getTimestamp).reversed();
    }

}
