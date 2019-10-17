package movies.controller;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MetricHandlerInterceptor implements HandlerInterceptor {
    private final MeterRegistry meterRegistry;

    public MetricHandlerInterceptor(final MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }


    @Override
    public void afterCompletion(final HttpServletRequest request, final HttpServletResponse response, final Object handler, final Exception ex) {
        final String uri = request.getRequestURI();
        final String method = request.getMethod();
        final String pathKey = method.concat(uri.toLowerCase()).replace("/", "_");

        meterRegistry.counter(pathKey).increment();
        meterRegistry.counter("HTTP_" + response.getStatus()).increment();
    }
}
