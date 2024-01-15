package ro.amait.resilience;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;

import static io.github.resilience4j.bulkhead.annotation.Bulkhead.Type.SEMAPHORE;
import static io.github.resilience4j.bulkhead.annotation.Bulkhead.Type.THREADPOOL;

@Service
@RequiredArgsConstructor
public class ExternalApiCallService {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CircuitBreakerRegistry circuitBreakerRegistry;


    public String callExternalCircuitBreakerApi(){
        CircuitBreaker circuitBreaker = circuitBreakerRegistry.circuitBreaker("externalServiceCircuitBreaker",
                "externalServiceCircuitBreakerConfig");
        return circuitBreaker.executeSupplier(
                () -> restTemplate.getForObject("/warehouse/unstable/external-foo-circuit-breaker",String.class));
    }

    @Retry(name = "retryApi", fallbackMethod = "fallbackAfterRetry")
    public String callExternalRetryApi(){
        System.out.println("Making a request for retry " + Instant.now());
        return restTemplate.getForObject("/warehouse/unstable/external-foo-retry",String.class);
    }

    public String fallbackAfterRetry(Exception ex) {
        return "all retries have exhausted";
    }

    @RateLimiter(name = "rateLimiterApi")
    public int callExternalRateLimiter() {
        System.out.println("Going to call rate limiter api");
        ResponseEntity<String> response = restTemplate.getForEntity("/warehouse/unstable/external-foo-rate-limiter",String.class);
        return response.getStatusCode().value();
    }

    public String callApiWithDelay() {
        String result = restTemplate.getForObject("/warehouse/unstable/external-foo-time-limiter", String.class);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ignore) {
        }
        return result;
    }


    @Bulkhead(name="bulkheadApi", type = SEMAPHORE)
    public int callBulkeadApi() throws Exception {
        System.out.println("Before call of bulkhead api");
        ResponseEntity<String> response = restTemplate.getForEntity("/warehouse/unstable/external-foo-bulkhead",String.class);
        System.out.println("Going to call bulkhead api "+ response.getStatusCode().value() +response.getBody());
        return response.getStatusCode().value();
    }

    public String fallbackAfterBulkhead(Exception ex) {
        System.out.println("Bulkhead fallback log");
        return "bulkHeadFallback";
    }

}
