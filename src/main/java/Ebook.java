import java.math.BigDecimal;
import java.util.List;

public class Ebook extends Book {
    List<String> supportedFormats;

    public Ebook(long id, String name, BigDecimal price, String author, int pageCounter) {
        super(id, name,price, author, pageCounter);
    }
}
