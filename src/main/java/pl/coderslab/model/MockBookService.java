package pl.coderslab.model;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MockBookService implements BookService {

    private final List<Book> list;

    public MockBookService() {
        // Inicjalizacja przykładowymi danymi
        list = new ArrayList<>();
        list.add(new Book(1L, "9788324631766", "Thinking in Java", "Bruce Eckel", "Helion", "programming"));
        list.add(new Book(2L, "9780134685991", "Clean Code", "Robert C. Martin", "Prentice Hall", "programming"));
    }

    @Override
    public List<Book> getBooks() {
        return list;
    }

    @Override
    public Optional<Book> get(Long id) {
        return list.stream().filter(book -> book.getId().equals(id)).findFirst();
    }

    @Override
    public void add(Book book) {
        long nextId = list.isEmpty() ? 1 : list.get(list.size() - 1).getId() + 1;
        book.setId(nextId);
        list.add(book);
    }

    @Override
    public void delete(Long id) {
        list.removeIf(book -> book.getId().equals(id));
    }

    @Override
    public void update(Book book) {
        int index = list.indexOf(get(book.getId()).orElse(null));
        if (index != -1) {
            list.set(index, book);
        }
    }
}
