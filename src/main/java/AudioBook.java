import java.math.BigDecimal;
import java.time.LocalDate;

public class AudioBook extends Book {
    int duration;
    private LocalDate publishmentDate;

    public AudioBook(long id, String name, BigDecimal price, String author, int pageCounter, LocalDate publishmentDate) {
        super( id, name, price, author, pageCounter );
        this.publishmentDate = publishmentDate;
    }

    public BigDecimal audioDiscount() {
        return audioDiscount();
    }
}
