import java.math.BigDecimal;
import java.util.List;

public class Cart {
    private final List<Product> productsInCart;

    public Cart(List<Product> productsInCart) {
        this.productsInCart = productsInCart;
    }

    public void addProductToCart(Product product) {
        productsInCart.add( product );
    }

    public void removeFromCart(Product product) {
        productsInCart.remove( product );
    }

    public void removeProductWithIndex(int index) {
        productsInCart.remove( index );
    }


    public BigDecimal getCartValue() {
        System.out.println( "Cena; %s, %d, %f" );
        return productsInCart
                .stream()
                .map( product -> product.getPrice() )
                .reduce( BigDecimal.ZERO, (sumPrice, actualPrice) -> sumPrice.add( actualPrice ) );

        // String.format("Cena: %s, %d,  %f");


    }

    public BigDecimal getDiscount(Product product) {
        BigDecimal discount = BigDecimal.ZERO;

        // BigDecimal audioDiscount = new BigDecimal("9.5");
        BigDecimal bookDiscount = new BigDecimal( "4" );
        // BigDecimal gameDiscount = new BigDecimal("0.33");
        if (product instanceof Book) {
            Book book = (Book) product;
            book.bookoDiscount( bookDiscount );
        }
        return discount;
//        discount = discount.add(audioDiscount).add(bookDiscount ).add(gameDiscount);
//        System.out.println(discount);
//return discount;
        //}

    }

    public BigDecimal calculateDiscountForProduct(Poster poster) {

        return null;
    }
}