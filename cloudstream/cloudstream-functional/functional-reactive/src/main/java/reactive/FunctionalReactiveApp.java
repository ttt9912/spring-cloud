package reactive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;

import java.util.function.Function;

/*
 * https://spring.io/blog/2019/10/17/spring-cloud-stream-functional-and-reactive
 * https://github.com/spring-cloud/spring-cloud-stream/blob/master/docs/src/main/asciidoc/spring-cloud-stream.adoc#spring_cloud_function
 *
 *
 */
@SpringBootApplication
public class FunctionalReactiveApp {

    public static void main(final String[] args) {
        SpringApplication.run(FunctionalReactiveApp.class, args);
    }

    @Bean
    public Function<Flux<String>, Flux<String>> uppercaseReactive() {
        return flux -> flux.map(String::toUpperCase);
    }
}
