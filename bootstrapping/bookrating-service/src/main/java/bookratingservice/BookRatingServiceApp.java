package bookratingservice;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

/*
 * Runs on random port
 *
 * -----------------------------------------------------------------------------
 * registry-aware client
 * -----------------------------------------------------------------------------
 * - consume BookService & RatingService REST endpoints via discovery
 *
 * -----------------------------------------------------------------------------
 * EurekaClient
 * -----------------------------------------------------------------------------
 * - find services manually
 * - not used with Feign
 *
 * -----------------------------------------------------------------------------
 * Browser (via Gateway)
 * -----------------------------------------------------------------------------
 * http://localhost:8080/bookrating-service/bookratings
 *
 */
@EnableFeignClients
@SpringBootApplication
public class BookRatingServiceApp {

    public static void main(final String[] args) {
        SpringApplication.run(BookRatingServiceApp.class, args);
    }

    @Bean
    CommandLineRunner lookupService(final EurekaClient eurekaClient) {
        return args -> {
            final InstanceInfo instanceInfo = eurekaClient.getNextServerFromEureka("config-server", false);
            System.out.println(">>> config-server Hostname: " + instanceInfo.getHostName());
            System.out.println(">>> config-server IP: " + instanceInfo.getIPAddr());
        };
    }
}
