package bookservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

/*
 * @EnableEurekaClient - registers the app to the Eureka Server (discovery-server)
 * and lets it use its services
 *
 * Runs on localhost:15100
 *
 * Browser => http://localhost:8080/book-service/books
 * - Gateway server will route /book-service/* to BookService app
 */
@SpringBootApplication
@EnableEurekaClient
public class BookServiceApp {

    public static void main(String[] args) {
        SpringApplication.run(BookServiceApp.class, args);
    }

    @Bean
    CommandLineRunner ctx(@Value("${spring.application.name}") String appName) {
        return args -> {
            System.out.println(">> defined in Config Repo: spring.application.name=" + appName);
        };
    }
}
