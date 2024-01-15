package ro.amait.resilience;

import io.github.resilience4j.bulkhead.BulkheadFullException;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.concurrent.TimeoutException;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler({CallNotPermittedException.class})
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public void handleCallNotPermitted() {
        System.out.println("Inside handle of service unavailable");
    }


    @ExceptionHandler({ RequestNotPermitted.class })
    @ResponseStatus(HttpStatus.TOO_MANY_REQUESTS)
    public void handleRequestNotPermited() {
        System.out.println("Inside handle of to many requests");
    }

    @ExceptionHandler({TimeoutException.class})
    @ResponseStatus(HttpStatus.REQUEST_TIMEOUT)
    public void handleTimeoutException() {
        System.out.println("Inside handle of timeout exception");
    }

    @ExceptionHandler({ BulkheadFullException.class })
    @ResponseStatus(HttpStatus.BANDWIDTH_LIMIT_EXCEEDED)
    public void handleBulkheadFullException() {
        System.out.println("Inside handle of bulkhead exception");
    }

}
