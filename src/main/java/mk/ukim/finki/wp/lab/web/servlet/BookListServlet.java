//package mk.ukim.finki.wp.lab.web.servlet;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import mk.ukim.finki.wp.lab.exceptions.InvalidBookIdException;
//import mk.ukim.finki.wp.lab.model.Book;
//import mk.ukim.finki.wp.lab.service.BookService;
//import org.thymeleaf.context.WebContext;
//import org.thymeleaf.spring6.SpringTemplateEngine;
//import org.thymeleaf.web.IWebExchange;
//import org.thymeleaf.web.servlet.JakartaServletWebApplication;
//
//import java.io.IOException;
//
//@WebServlet(name="book-list", urlPatterns = "/listBooks")
//public class BookListServlet extends HttpServlet {
//    private final SpringTemplateEngine springTemplateEngine;
//    private final BookService bookService;
//
//    public BookListServlet(SpringTemplateEngine springTemplateEngine, BookService bookService) {
//        this.springTemplateEngine = springTemplateEngine;
//        this.bookService = bookService;
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        IWebExchange webExchange = JakartaServletWebApplication
//                .buildApplication(getServletContext())
//                .buildExchange(req, resp);
//        WebContext context =  new WebContext(webExchange);
//        context.setVariable("books",bookService.listBooks());
//        springTemplateEngine.process("listBooks.html",context,resp.getWriter());
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String id = (String) req.getParameter("bookId");
//        req.getSession().setAttribute("bookId",id);
//        Book book = bookService.findBookById(Long.valueOf(id)).orElseThrow(InvalidBookIdException::new);
//        req.getSession().setAttribute("book",book);
//        resp.sendRedirect("/author");
//    }
//}
