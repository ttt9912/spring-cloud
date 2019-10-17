package movies.redis;

import lombok.extern.slf4j.Slf4j;
import movies.csvimport.MovieImportService;
import movies.data.Movie;
import movies.data.MovieRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class RedisDataInitializer implements ApplicationRunner {
    private final MovieRepository repository;
    private final MovieImportService movieImportService;

    public RedisDataInitializer(final MovieRepository repository, final MovieImportService movieImportService) {
        this.repository = repository;
        this.movieImportService = movieImportService;
    }

    @Override
    public void run(final ApplicationArguments args) throws Exception {
        final List<Movie> movies = movieImportService.importMovies();
        repository.saveAll(movies);
        log.info(">> saved {} movies", movies.size());
    }
}
