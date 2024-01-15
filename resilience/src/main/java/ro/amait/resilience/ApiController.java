package ro.amait.resilience;


import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.ZoneOffset;
import java.util.Map;
import java.util.concurrent.*;
import java.util.stream.IntStream;

@RestController
@RequiredArgsConstructor
public class ApiController {

    @Autowired
    ExternalApiCallService externalApiCall;

    @GetMapping("/circuit-breaker")
     public String circuitBreakerApi() throws InterruptedException {

        Map<Integer, Integer> responseStatusCount = new ConcurrentHashMap<>();
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        CountDownLatch latch = new CountDownLatch(10);
        IntStream.rangeClosed(1,60)
                .forEach(i -> executorService.execute(() -> {
                    try {
                        Thread.sleep(1000);
                        System.out.println("Call no: "+i + " on second " + Instant.now().atZone(ZoneOffset.UTC).getSecond());
                         String response = externalApiCall.callExternalCircuitBreakerApi();
                        System.out.println("CircuitBreaker response: " + response);
                    }catch (Exception ex){
                        System.out.println("Exception calling circuit breaker" + ex);
                    }
                    latch.countDown();
                }));
        latch.await();
        executorService.shutdown();
        responseStatusCount.forEach((k,v)-> System.out.println(k+", "+v));
        return "ok";
     }

    @GetMapping("/retry")
    public String retryApi() {
        return externalApiCall.callExternalRetryApi();
    }

    @GetMapping("/rate-limiter")
    public String rateLimitApi() {
        Map<Integer, Integer> responseStatusCount = new ConcurrentHashMap<>();
        IntStream.rangeClosed(1, 50)
                .parallel()
                .forEach(i -> {
                    int statusCode = externalApiCall.callExternalRateLimiter();
                    responseStatusCount.put(statusCode, responseStatusCount.getOrDefault(statusCode, 0) + 1);
                });
        responseStatusCount.forEach((k,v)-> System.out.println(k+", "+v));
        return "ok";
    }


    @GetMapping("/time-limiter")
    @TimeLimiter(name = "timeLimiterApi")
    public CompletableFuture<String> timeLimiterApi() {
        return CompletableFuture.supplyAsync(externalApiCall::callApiWithDelay);
    }



    @GetMapping("/bulkhead")
    public String bulkheadApi() throws InterruptedException {
        Map<Integer, Integer> responseStatusCount = new ConcurrentHashMap<>();
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        CountDownLatch latch = new CountDownLatch(10);
        IntStream.rangeClosed(1, 10)
                .forEach(i -> executorService.execute(() -> {
                    try {

                        int statusCode = externalApiCall.callBulkeadApi();
                        responseStatusCount.merge(statusCode, 1, Integer::sum);

                    }catch (Exception ex){
                        System.out.println("Exception calling bulkhead api " + ex);
                    }

                    latch.countDown();
                }));
        latch.await();
        executorService.shutdown();
        responseStatusCount.forEach((k,v)-> System.out.println(k+", "+v));
        return "ok";
    }

}
