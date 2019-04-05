import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    @Test
    public void shouldReturnCorrectSumOfCart(){
        Poster POSTER_1 = new Poster(1, "nazwa1", new BigDecimal("10"));
        Poster POSTER_2 = new Poster(2, "nazwa2", new BigDecimal("2.50"));
        Poster POSTER_3 = new Poster(3, "nazwa3", new BigDecimal("25"));

        Cart cart = new Cart( Arrays.asList(POSTER_1, POSTER_2, POSTER_3));

        BigDecimal cartValue = cart.getCartValue();

        assertEquals(new BigDecimal("37.50"), cartValue);

    }


}