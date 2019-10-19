package movieratings.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rating { // extends Event

    @Id
    private LocalDateTime timestamp; // key

    private String movieId;
    private Double rating;

    public String createKey() {
        return timestamp.format(DateTimeFormatter.ISO_DATE_TIME);
    }
}
