package live_coding.ood.onlineCloudReadingApp;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();

        List<String> content1 = Arrays.asList("b1p1", "b1p2", "b1p3");
        Book b1 = new Book(content1, "title1");

        List<String> content2 = Arrays.asList("b2p1", "b2p2", "b2p3", "b2p4", "b2p5");
        Book b2 = new Book(content2, "title2");

        library.addBook(b1);
        library.addBook(b2);

        library.setActiveBookId(b1.getId());
        b1.setLastPageNumber(2);

        System.out.println(library.displayPage());
    }
}
