package zipkinserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.HttpEncodingAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import zipkin2.server.internal.EnableZipkinServer;

/*
 * Zipkin server - serves as store for all spans. Each span is sent to this
 * service and collected into traces for future identification.
 *
 * Starts a Zipkin server
 *
 * ------------------------------------
 * Zipkin UI
 * ------------------------------------
 * http://localhost:9411/zipkin/
 */
@SpringBootApplication(exclude = HttpEncodingAutoConfiguration.class) // TODO
// remove spring-web dependency and solve logger problem
@EnableDiscoveryClient
@EnableZipkinServer
public class ZipkinServerApp {

    public static void main(String[] args) {
        SpringApplication.run(ZipkinServerApp.class, args);
    }
}
