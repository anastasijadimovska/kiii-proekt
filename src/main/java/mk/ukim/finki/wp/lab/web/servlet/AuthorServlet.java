//package mk.ukim.finki.wp.lab.web.servlet;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import mk.ukim.finki.wp.lab.exceptions.InvalidAuthorException;
//import mk.ukim.finki.wp.lab.model.Author;
//import mk.ukim.finki.wp.lab.model.Book;
//import mk.ukim.finki.wp.lab.service.AuthorService;
//import mk.ukim.finki.wp.lab.service.BookService;
//import org.thymeleaf.context.WebContext;
//import org.thymeleaf.spring6.SpringTemplateEngine;
//import org.thymeleaf.web.IWebExchange;
//import org.thymeleaf.web.servlet.JakartaServletWebApplication;
//
//import java.io.IOException;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@WebServlet(name = "author-servlet", urlPatterns = "/author")
//public class AuthorServlet extends HttpServlet {
//    private final SpringTemplateEngine springTemplateEngine;
//    private final AuthorService authorService;
//    private final BookService bookService;
//
//    public AuthorServlet(SpringTemplateEngine springTemplateEngine, AuthorService authorService, BookService bookService) {
//        this.springTemplateEngine = springTemplateEngine;
//        this.authorService = authorService;
//        this.bookService = bookService;
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        IWebExchange webExchange = JakartaServletWebApplication
//                .buildApplication(getServletContext())
//                .buildExchange(req, resp);
//        WebContext context =  new WebContext(webExchange);
//        context.setVariable("authors",authorService.listAuthors());
//        Long id = (Long) req.getSession().getAttribute("authorId");
//        this.springTemplateEngine.process("authorList.html",context,resp.getWriter());
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String reqId = req.getParameter("authorId");
//        Long id = Long.parseLong(reqId);
//       req.getSession().setAttribute("author",authorService.findById(id));
//        Author author = (Author) req.getSession().getAttribute("author");
//        String idB = (String) req.getSession().getAttribute("bookId");
//        bookService.addAuthorToBook(author.getId(), Long.valueOf(idB));
//
//        Book book = (Book) req.getSession().getAttribute("book");
//        List<Author> list = book.getAuthors();
//
//        req.getSession().setAttribute("authors",list);
//        resp.sendRedirect("/bookDetails");
//    }
//}
