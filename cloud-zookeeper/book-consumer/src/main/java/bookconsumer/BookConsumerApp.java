package bookconsumer;

import bookconsumer.feign.BookClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

/*
 * Calls REST endpoints from book-provider via ZooKeeper
 */
@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class BookConsumerApp {

    public static void main(String[] args) {
        SpringApplication.run(BookConsumerApp.class, args);
    }

    @Bean
    CommandLineRunner run(final BookClient bookClient) {
        return args -> bookClient.findAll().forEach(System.out::println);
    }
}
