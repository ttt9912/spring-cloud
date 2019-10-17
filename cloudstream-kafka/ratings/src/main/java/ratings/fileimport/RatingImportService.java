package ratings.fileimport;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ratings.data.Rating;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class RatingImportService {
    private final ObjectMapper objectMapper;

    public RatingImportService(final ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public List<Rating> importRatings(final File file) {
        log.info(">> reading file {}", file.getAbsolutePath());
        return readFile(file).stream()
                .map(this::createRating)
                .collect(Collectors.toList());
    }

    private Rating createRating(final RatingImportDto ratingImportDto) {
        return new Rating(LocalDateTime.now(),
                String.valueOf(ratingImportDto.getMovieId()),
                ratingImportDto.getRating());
    }

    private List<RatingImportDto> readFile(final File file) {
        try {
            return objectMapper.readValue(file, new TypeReference<List<RatingImportDto>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
