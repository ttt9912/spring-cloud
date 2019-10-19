package movies.data;

import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<Movie, String> {
}
