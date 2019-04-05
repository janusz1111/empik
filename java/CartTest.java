//import dto.products.printed.Poster;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class CartTest {

 Cart cart = new Cart();

    @Test
    @DisplayName("should calculate correct discount for poster when 3")
    void shouldCalculateCorrectDiscountForPoster(){
        //given

        Poster POSTER_1 = new Poster(1, "nazwa1", new BigDecimal("10.24"));
        Poster POSTER_2 = new Poster(2, "nazwa2", new BigDecimal("2.50"));
        Poster POSTER_3 = new Poster(3, "nazwa3", new BigDecimal("25"));

        cart.addProductToCart(POSTER_1);
        cart.addProductToCart(POSTER_2);
        cart.addProductToCart(POSTER_3);

        //when
        BigDecimal priceAfterDiscount_1 = cart.calculateDiscountForProduct(POSTER_1);
        BigDecimal priceAfterDiscount_2 = cart.calculateDiscountForProduct(POSTER_2);
        BigDecimal priceAfterDiscount_3 = cart.calculateDiscountForProduct(POSTER_3);

        //then
        assertEquals(new BigDecimal("10.24"), priceAfterDiscount_1);
        assertEquals(new BigDecimal("1"), priceAfterDiscount_2);
        assertEquals(new BigDecimal("25"), priceAfterDiscount_3);
    }

    @Test
    @DisplayName("should set discount one for max one of posters with same price")
    void test3(){
        BigDecimal expectedPrice = new BigDecimal("10.24");
        Poster POSTER_1 = new Poster(1, "nazwa1", expectedPrice);
        Poster POSTER_2 = new Poster(2, "nazwa2", expectedPrice);
        Poster POSTER_3 = new Poster(3, "nazwa3", expectedPrice);

        cart.addProductToCart(POSTER_1);
        cart.addProductToCart(POSTER_2);
        cart.addProductToCart(POSTER_3);

        BigDecimal priceAfterDiscount_1 = cart.calculateDiscountForProduct(POSTER_1);
        BigDecimal priceAfterDiscount_2 = cart.calculateDiscountForProduct(POSTER_2);
        BigDecimal priceAfterDiscount_3 = cart.calculateDiscountForProduct(POSTER_3);

        assertEquals(new BigDecimal("1"), priceAfterDiscount_1);
        assertEquals(new BigDecimal("10.24"), priceAfterDiscount_2);
        assertEquals(new BigDecimal("10.24"), priceAfterDiscount_3);

    }

    @Test
    public void shouldReturnCorrectSumOfCart(){
        Poster POSTER_1 = new Poster(1, "nazwa1", new BigDecimal("10"));
        Poster POSTER_2 = new Poster(2, "nazwa2", new BigDecimal("2.50"));
        Poster POSTER_3 = new Poster(3, "nazwa3", new BigDecimal("25"));

        Cart cart = new Cart(Arrays.asList(POSTER_1, POSTER_2, POSTER_3));

        BigDecimal cartValue = cart.getCartValue();

        assertEquals(new BigDecimal("37.50"), cartValue);

    }

}