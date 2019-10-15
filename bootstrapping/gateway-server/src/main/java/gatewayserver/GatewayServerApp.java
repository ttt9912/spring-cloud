package gatewayserver;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

/*
 * - Reverse Proxy
 * - Routes incoming requests to corresponding url
 * - Routes are defined in Git config repository
 *
 * ------------------------------------------------------------------
 * Defined Routes
 * ------------------------------------------------------------------
 * localhost:8080/book-service/books            ==>   localhost:8083 (Book Service)
 * localhost:8080/rating-service/ratings_json/all    ==>   localhost:8084 (Rating Service)
 *
 */
@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
public class GatewayServerApp {

    public static void main(String[] args) {
        SpringApplication.run(GatewayServerApp.class, args);
    }

    @Bean
    CommandLineRunner ctx(
            @Value("${spring.application.name}") String appName) {
        return args -> {
            System.out.println(">> defined in Config Repo: spring.application.name=" + appName);
        };
    }

}
