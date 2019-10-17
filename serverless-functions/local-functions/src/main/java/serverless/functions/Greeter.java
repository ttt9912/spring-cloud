package serverless.functions;

import java.util.function.Function;

/*
 * curl localhost:8080/greeter -H "Content-Type: text/plain" -d "World"
 */
public class Greeter implements Function<String, String> {

    @Override
    public String apply(final String name) {
        return "Hello ".concat(name);
    }

}
