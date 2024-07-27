package live_coding.ood.onlineCloudReadingApp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Library {
    private Map<String, Book> booksMap = new HashMap<>();
    private String activeBookId;

    public Library(List<Book> books) {
        for (Book book : books) {
            booksMap.put(book.getId(), book);
        }
    }

    public Library() {
    }

    //FR:
    public void addBook(Book book) {
        this.booksMap.put(book.getId(), book);
    }

    public void removeBook(Book book) {
        this.booksMap.remove(book.getId());
    }

    public void setActiveBookId(String bookId) {
        this.activeBookId = bookId;
    }

    public String displayPage() {
        Book activeBook = booksMap.get(this.activeBookId);
        return activeBook.displayPage();
    }

    public String turnPage() {
        Book activeBook = booksMap.get(this.activeBookId);
        return activeBook.turnPage();
    }
}
