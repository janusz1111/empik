import java.math.BigDecimal;

public class Track extends Product{
    String name;
    long duration;

    public Track(long id, String name, BigDecimal price) {
        super(id, name, price);
        this.name = name;
        this.duration = duration;
    }
}
