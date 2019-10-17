package reversestring.lambda;

import org.springframework.cloud.function.adapter.aws.SpringBootRequestHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

/*
 * empty class
 *
 * - acting as the entry point of the Lambda function and
 * - defining its input and output types
 *
 * We provide the fully qualified name of this class in the
 * Handler input field of the AWS Lambda configuration page
 */
@Configuration
public class MyStringHandlers extends SpringBootRequestHandler<String, String> {
    @Bean
    public Function<String, String> reverseString() {
        return value -> new StringBuilder(value).reverse().toString();
    }
}
