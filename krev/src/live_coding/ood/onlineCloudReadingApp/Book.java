package live_coding.ood.onlineCloudReadingApp;

import java.util.List;
import java.util.UUID;

public class Book {
    private final String bookId;
    private final String title;
    private final List<String> content; //per page
    private int lastPageNumber = 0;

    //follow-up: what if we need zoom in the text?
    private String contentStr;   //very long string. lets us work with font size (whereas List<String> does not let)
    private int fontSize = 12;

    public Book(List<String> content, String title) {
        this.bookId = UUID.randomUUID().toString();
        this.content = content;
        this.title = title;
    }

    public String getId() {
        return bookId;
    }

    //follow-up: what if we need zoom in the text?
//    public void setFontSize(int fontSize) {
//        this.fontSize = fontSize;
//    }

    public void setLastPageNumber(int pageNumber) {
        this.lastPageNumber = pageNumber;
    }

    //FRs
    public String displayPage() {
        return content.get(this.lastPageNumber);
    }

    //follow-up: what if we need zoom in the text?
    public String displayPage2() {
        int charsPerPage = contentStr.length()/fontSize;
        int startIdx = charsPerPage * lastPageNumber;
        int endIdx = startIdx + charsPerPage;
        return contentStr.substring(startIdx, endIdx + 1);
    }


    public String turnPage() {
        this.lastPageNumber++;
        return displayPage();
    }
}
