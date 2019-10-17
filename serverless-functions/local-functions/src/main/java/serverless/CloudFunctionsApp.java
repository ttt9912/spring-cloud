package serverless;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Function;
import java.util.function.Supplier;

/*
 * ---------------------------------------------------------------------------------
 * Spring Cloud Function Methods
 * ---------------------------------------------------------------------------------
 * - expose @Beans of type Function, Consumer or Supplier as individual methods
 * - spring-cloud-starter-function-web exposes the function as an HTTP endpoint
 *
 * ---------------------------------------------------------------------------------
 * Spring Cloud Function Classes
 * ---------------------------------------------------------------------------------
 * - implement the functional interface Function<T, R>
 * - scan function packages with property
 *      spring.cloud.function.scan.packages=<package name>
 *
 * ---------------------------------------------------------------------------------
 * Exposed Functions via HTTP
 * ---------------------------------------------------------------------------------
 * - localhost:8080/<name of the bean>
 *
 * ---------------------------------------------------------------------------------
 * Call functions
 * ---------------------------------------------------------------------------------
 * curl localhost:8080/reverseString -H "Content-Type: text/plain" -d "Baeldung User"
 * curl localhost:8080/greeter -H "Content-Type: text/plain" -d "World"
 *
 */
@SpringBootApplication
public class CloudFunctionsApp {

    public static void main(String[] args) {
        SpringApplication.run(CloudFunctionsApp.class, args);
    }

    /*
     * automatically exposed to
     * localhost:8080/reverseString
     */
    @Bean
    public Function<String, String> reverseString() {
        return value -> new StringBuilder(value).reverse().toString();
    }

    @Bean
    public Supplier<String> planForToday() {
        return () -> "sipping cocktails in the sun, enjoying the sunshine";
    }
}
