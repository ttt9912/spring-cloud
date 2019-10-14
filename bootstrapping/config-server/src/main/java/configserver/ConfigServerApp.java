package configserver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

/*
 * @EnableConfigServer - turn application into a configuration server
 * - application properties are managed by this config server
 *
 * ---------------------------------------------------------------------------------
 * Exposes Configs via REST
 * ---------------------------------------------------------------------------------
 * /{application}/{profile}/{branch}
 * /{application}-{profile}.{properties|yml}
 * /{branch}/{application}-{profile}.{properties|yml}
 *
 * - curl localhost:8081/gateway-server.properties
 *
 * # secured
 * - curl localhost:8081/gateway-server.properties -u root:s3cr3t
 * or
 * - curl http://root:s3cr3t@localhost:8081/gateway-server.properties
 *
 * ---------------------------------------------------------------------------------
 * Change properties at runtime
 * ---------------------------------------------------------------------------------
 * TODO
 * - RefreshScope
 * - POST actuator/refresh
 * - EnvironmentChangeEvent
 */
@Slf4j
@EnableConfigServer
@EnableEurekaClient
@SpringBootApplication
public class ConfigServerApp {

    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApp.class, args);
    }


    @Bean
    CommandLineRunner ctx(ApplicationContext context) {
        return args -> {
            System.out.println(">>> " + context.getEnvironment().getProperty("spring.security.user.password"));
        };
    }

}
