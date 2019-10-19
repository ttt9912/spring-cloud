package ratings.fileimport;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Value;

@Value
@JsonIgnoreProperties(ignoreUnknown = true)
public class RatingImportDto {
    private Long movieId;
    private Double rating;
}
