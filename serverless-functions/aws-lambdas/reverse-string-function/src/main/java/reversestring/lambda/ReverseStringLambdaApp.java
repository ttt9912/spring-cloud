package reversestring.lambda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Function;

/*
 * ---------------------------------------------------------------------------------
 * AWS Management Console - Lambdas
 * ---------------------------------------------------------------------------------
 * https://console.aws.amazon.com/lambda/home?region=us-east-1#/functions
 *
 * ---------------------------------------------------------------------------------
 * Test
 * ---------------------------------------------------------------------------------
 * create Tests in the AWS Management Console
 *
 * ---------------------------------------------------------------------------------
 * Test local
 * ---------------------------------------------------------------------------------
 * curl localhost:8080/reverseString -H "Content-Type: text/plain" -d "Baeldung User"
 *
 */
@SpringBootApplication
public class ReverseStringLambdaApp {

    public static void main(final String[] args) {
        SpringApplication.run(ReverseStringLambdaApp.class, args);
    }

    @Bean
    public Function<String, String> reverseString() {
        return value -> new StringBuilder(value).reverse().toString();
    }

    /*
    @Bean
    public Supplier<String> planForToday() {
        return () -> "sipping cocktails in the sun, enjoying the sunshine";
    }
    */
}
