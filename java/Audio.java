import javax.sound.midi.Track;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


public class Audio extends Product{
    LocalDate publishmentDate;
    List<Track> track;

    public LocalDate getPublishmentDate() {
        return publishmentDate;
    }

    public List<Track> getTrack() {
        return track;
    }

    public Audio(long id, String name, BigDecimal price, LocalDate publishmentDate, List<Track> track) {
        super( id, name, price );
        this.publishmentDate = publishmentDate;
        this.track = track;
    }

}
