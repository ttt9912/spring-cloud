package movies;

import io.micrometer.core.instrument.MeterRegistry;
import movies.controller.MetricHandlerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.MappedInterceptor;

@Configuration
public class Config {


    /*
     * Metrics Interceptor
     */
    @Bean
    public MappedInterceptor metricInterceptor(MeterRegistry meterRegistry) {
        return new MappedInterceptor(new String[]{"/**"}, new MetricHandlerInterceptor(meterRegistry));
    }
}
