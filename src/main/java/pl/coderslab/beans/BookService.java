package pl.coderslab.beans;

import java.util.List;

public interface BookService {

    List<Book> getList();

    void setList(List<Book> list);

    Book getBook(Long id);

    void setBook(Book book);

    void editBook(Long id, String isbn, String title, String author, String publisher, String type);

    void deleteBook(Long id);
}
