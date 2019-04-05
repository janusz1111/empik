import java.math.BigDecimal;

public class Book extends Product {
    private final String author;
    private final int pageCounter;

    public Book(long id, String name, BigDecimal price, String author, int pageCounter) {
        super( id, name, price );
        this.pageCounter = pageCounter;
        this.author = author;
    }

    public BigDecimal SalePrice(BigDecimal discount) {
        int percent = 0;
        if (pageCounter > 400) percent = 40;
        else {

            percent = 10*(pageCounter / 100);
        }
        BigDecimal percentDiscount = new BigDecimal( percent );
        BigDecimal sto = new BigDecimal(100);
        BigDecimal salePrice = sto.subtract(percentDiscount.divide(sto).multiply(getPrice()));
        return salePrice;
    }
}
