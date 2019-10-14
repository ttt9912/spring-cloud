package bookratingservice;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

/*
 * consume BookService & RatingService
 *
 * TODO: feign, hystrix, etc... projects
 *
 */
@SpringBootApplication
@EnableEurekaClient
public class BookRatingService {

    public static void main(String[] args) {
        SpringApplication.run(BookRatingService.class, args);
    }

    @Bean
    CommandLineRunner lookupService(EurekaClient eurekaClient) {
        return args -> {
            InstanceInfo instanceInfo = eurekaClient.getNextServerFromEureka("config-server", false);
            System.out.println(">>> config-server Hostname: " + instanceInfo.getHostName());
            System.out.println(">>> config-server IP: " + instanceInfo.getIPAddr());
        };
    }
}
