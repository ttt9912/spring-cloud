package bookratingservice.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookRating {
    private String author;
    private String title;
    private Double stars;
}
