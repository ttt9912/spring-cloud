package discoveryserver;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Bean;

/*
 * TODO actuator/health for all services
 */
@SpringBootApplication
@EnableEurekaServer
public class DiscoveryServerApp {

    public static void main(String[] args) {
        SpringApplication.run(DiscoveryServerApp.class, args);
    }


    @Bean
    CommandLineRunner ctx(
            @Value("${spring.application.name}") String appName) {
        return args -> {
            System.out.println(">> defined in Config Repo: spring.application.name=" + appName);
        };
    }
}
