package com.storage.controller;

import javax.annotation.security.PermitAll;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;


@Path("/unstable")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class NotSoStableController {

    final Counter counter = new Counter();

    @GET
    @Path("/external-foo-retry")
    @PermitAll
    public Response InstableRetry() {
        if (Instant.now().atZone(ZoneOffset.UTC).getMinute() % 2 == 0) {
            return Response.status(503).build();
        }
        String tmp = "It Works - External Retry";
        return Response.ok(tmp).build();
    }

    @GET
    @Path("/external-foo-circuit-breaker")
    @PermitAll
    public Response InstableCircuitBreaker() {
        if ((Instant.now().atZone(ZoneOffset.UTC).getSecond() >= 20) && (Instant.now().atZone(ZoneOffset.UTC).getSecond() <= 30)) {
            return Response.status(503).build();
        }

        String tmp = "It Works - External Circuit Breaker";
        return Response.ok(tmp).build();
    }

    @GET
    @Path("/external-foo-rate-limiter")
    @PermitAll
    public Response InstableRateLimiter() {
        String tmp = "It Works with Limit - Rate Limiter";
        return Response.ok(tmp).build();
    }

    @GET
    @Path("/external-foo-time-limiter")
    @PermitAll
    public Response InstableTimeLimiter() {
        String tmp = "It Works with Limit - Time Limiter";
        return Response.ok(tmp).build();
    }

    @GET
    @Path("/external-foo-bulkhead")
    @PermitAll
    public Response InstableBulkhead() throws InterruptedException {

        counter.increment();
        Random random = new Random();
        Integer sleepTime = random.ints(6000, 8000)
                .findFirst()
                .getAsInt();
        Thread.sleep(sleepTime);
        String tmp = "It Works with Bulkhead " + sleepTime + " " + counter.value();

        counter.decrement();

        return Response.ok(tmp).build();
    }

    static class Counter {
        private AtomicInteger c = new AtomicInteger(0);

        public void increment() {
            c.getAndIncrement();
        }

        public void decrement() {
            c.decrementAndGet();
        }

        public int value() {
            return c.get();
        }
    }
}
