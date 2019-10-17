package reversestring.lambda;

import org.springframework.cloud.function.adapter.aws.SpringBootRequestHandler;

/*
 * empty class
 *
 * - acting as the entry point of the Lambda function and
 * - defining its input and output types
 *
 * We provide the fully qualified name of this class in the
 * Handler input field of the AWS Lambda configuration page
 */
public class MyStringHandlers extends SpringBootRequestHandler<String, String> {

}
