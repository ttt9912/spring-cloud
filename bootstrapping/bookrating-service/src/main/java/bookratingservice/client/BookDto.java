package bookratingservice.client;

import lombok.Value;

@Value
public class BookDto {
    private Long id;
    private String author;
    private String title;
}
