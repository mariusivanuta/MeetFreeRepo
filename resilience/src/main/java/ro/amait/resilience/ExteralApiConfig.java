package ro.amait.resilience;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.common.circuitbreaker.configuration.CircuitBreakerConfigCustomizer;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.Map;

import static io.github.resilience4j.circuitbreaker.CircuitBreakerConfig.SlidingWindowType.COUNT_BASED;

@Configuration
public class ExteralApiConfig {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplateBuilder().rootUri("http://localhost:9090").build();
    }

    @Bean
    public CircuitBreakerRegistry circuitBreakerRegistry() {
        CircuitBreakerConfig externalServiceCircuitBreakerConfig = CircuitBreakerConfig.custom()
                .slidingWindowSize(10)
                .slidingWindowType(COUNT_BASED)
                .waitDurationInOpenState(Duration.ofSeconds(5))
                .minimumNumberOfCalls(5)
                .failureRateThreshold(50.0f)
                .build();
        return CircuitBreakerRegistry.of(
                Map.of("externalServiceCircuitBreakerConfig", externalServiceCircuitBreakerConfig)
        );
    }
}
