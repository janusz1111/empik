import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static junit.framework.TestCase.assertTrue;

public class BookTest {

// Book book = new Book();
    Book book = new Book( 1, "book1", new BigDecimal( 100 ), "Janusz", 420 );

    @Test;

    void nazwa() {

        BigDecimal sale = new BigDecimal( 60 );


        assertTrue( sale.equals( "60") );

    }


}

