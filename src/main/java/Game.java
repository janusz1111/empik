import java.math.BigDecimal;

public class Game extends Product{
    String publisher;

    public Game(long id, String name, BigDecimal price) {
        super( id, name, price );
    }
}
