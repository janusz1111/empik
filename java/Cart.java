//mport dto.products.Product;
//import dto.products.ProductWithDiscount;
//import dto.products.audio.AudioAlbum;
//import dto.products.book.Book;
//import dto.products.game.Game;
//import dto.products.printed.Poster;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Cart {

    final List<Product> productsInCart;

    public Cart() {
        productsInCart = new LinkedList<>();
    }

    public Cart(List<Product> productsInCart) {
        this.productsInCart = productsInCart;
    }

    public void addProductToCart(Product product) {
        productsInCart.add(product);
    }

    public void removeFromCart(Product product) {
        productsInCart.remove(product);
    }

    public void removeProductWithIndex(int index) {
        productsInCart.remove(index);
    }

    public BigDecimal getCartValue() {
        return productsInCart
                .stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }


    public List<Product> getProductsInCart() {
        return productsInCart;
    }
}

