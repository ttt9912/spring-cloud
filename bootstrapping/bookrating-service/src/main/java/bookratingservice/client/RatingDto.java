package bookratingservice.client;

import lombok.Value;

@Value
public class RatingDto {
    private Long id;
    private Long bookId;
    private int stars;
}
