package pl.coderslab.beans;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemoryBookService implements BookService{
    private List<Book> list;

    public MemoryBookService() {
        list = new ArrayList<>();
        list.add(new Book("9788324631766", "Thinking in Java", "Bruce Eckel",
                "Helion", "programming"));
        list.add(new Book("9788324627738", "Rusz glowa, Java.",
                "Sierra Kathy, Bates Bert", "Helion", "programming"));
        list.add(new Book("9780130819338", "Java 2. Podstawy",
                "Cay Horstmann, Gary Cornell", "Helion", "programming"));
    }

    public List<Book> getList() {
        return list;
    }

    public void setList(List<Book> list) {
        this.list = list;
    }

    public Book getBook(Long id) {
        return list.stream().filter(book -> book.getId() == id).collect(Collectors.toList()).get(0);
    }

    public void setBook(Book book) {
        this.list.add(book);
    }

    public void editBook(Long id, String isbn, String title, String author, String publisher, String type) {
        Book book = list.stream()
                .filter(b -> b.getId() == id)
                .collect(Collectors.toList())
                .get(0);

        book.setIsbn(isbn)
                .setTitle(title)
                .setAuthor(author)
                .setPublisher(publisher)
                .setType(type);
    }

    public void deleteBook(Long id) {
        list.remove(list.stream().filter(book -> book.getId() == id).collect(Collectors.toList()).get(0));
    }
}
