import dto.products.Product;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ReceiptLine {
    private final long id;
    private final String name;
    private final BigDecimal price;
    private final BigDecimal priceAfterDiscount;

    private static final String LINE_FORMAT = "%s\t|%s\t|%s\t|%s";


    public ReceiptLine(Product product, BigDecimal priceAfterDiscount) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.priceAfterDiscount = priceAfterDiscount;
    }

    public static String getHeader(){
        return String.format(LINE_FORMAT, "id", "name", "price", "price after discount");
    }

    @Override
    public String toString(){
        return String.format(LINE_FORMAT, id, name, price, priceAfterDiscount.setScale(2, RoundingMode.HALF_UP));
    }
}
