import java.math.BigDecimal;
import java.time.LocalDate;

public class AudioBook extends Book{

    int duration;
    private LocalDate publishmentDate;

    public AudioBook(long id, String name, BigDecimal price, String author, int pageCounter, LocalDate publishmentDate) {
        super( id, name, price, author, pageCounter );
        this.publishmentDate = publishmentDate;
    }

    public int getDuration() {
        return duration;
    }

    public LocalDate getPublishmentDate() {
        return publishmentDate;
    }

    public BigDecimal audioDiscount() {

        return audioDiscount();
    }
    public BigDecimal SalePrice(BigDecimal discount) {

        int percent = (int) (LocalDate.now() - publishmentDate);

        BigDecimal percentDiscount = new BigDecimal( percent );
        BigDecimal sto = new BigDecimal(100);
        BigDecimal salePrice = sto.subtract(percentDiscount.divide(sto).multiply(getPrice()));
        return salePrice;
    }
}