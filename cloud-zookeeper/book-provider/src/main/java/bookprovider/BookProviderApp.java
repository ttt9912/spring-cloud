package bookprovider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/*
 * Registers itself on ZooKeeper
 */
@SpringBootApplication
@EnableDiscoveryClient
public class BookProviderApp {

    public static void main(String[] args) {
        SpringApplication.run(BookProviderApp.class, args);
    }
}
