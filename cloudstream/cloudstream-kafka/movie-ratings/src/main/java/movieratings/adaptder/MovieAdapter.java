package movieratings.adaptder;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class MovieAdapter {
    private final RestTemplate restTemplate;

    public MovieAdapter(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String findMovieName(final String id) {
        String uri = UriComponentsBuilder.fromHttpUrl("http://localhost:18000/api/movie/name")
                .queryParam("id", id)
                .toUriString();
        return restTemplate.getForObject(uri, String.class);
    }
}
