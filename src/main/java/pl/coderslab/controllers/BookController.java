package pl.coderslab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.beans.Book;
import pl.coderslab.beans.BookService;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    BookService bookService;

    @RequestMapping("/hello")
    public String hello(){
        return "{hello: World}";
    }
    @RequestMapping("/helloBook")
    public Book helloBook(){
        return new Book("9788324631766","Thinking in Java",
                "Bruce Eckel","Helion","programming");
    }

    @GetMapping("/")
    public List<Book> bookList(){
        return bookService.getList();
    }

    @PostMapping("/")
    public String addBook(@RequestBody Book book) {
        bookService.setBook(book);
        return "Book doDao";
    }

    @GetMapping("/{id}")
    public Book book(@PathVariable Long id){
        return bookService.getBook(id);
    }

    @PutMapping("/{id}")
    public String editBook(@PathVariable Long id, @RequestParam String isbn, @RequestParam String title, @RequestParam String author, @RequestParam String publisher, @RequestParam String type) {
        bookService.editBook(id, isbn, title, author, publisher, type);
        return "Book edytoWao";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return "Book usuNeo";
    }
}
