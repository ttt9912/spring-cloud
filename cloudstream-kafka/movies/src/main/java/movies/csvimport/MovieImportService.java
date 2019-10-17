package movies.csvimport;

import movies.data.Movie;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieImportService {
    private final CsvFileReader csvFileReader;

    public MovieImportService(final CsvFileReader csvFileReader) {
        this.csvFileReader = csvFileReader;
    }

    public List<Movie> importMovies() throws IOException {
        final InputStream source = new ClassPathResource("movies_csv/movies_metadata.csv").getInputStream();
        return csvFileReader.loadObjectList(MovieImportDto.class, source).stream()
                .map(this::createMovie)
                .collect(Collectors.toList());
    }

    private Movie createMovie(final MovieImportDto dto) {
        return new Movie(dto.getId(), dto.getTitle());
    }

}
