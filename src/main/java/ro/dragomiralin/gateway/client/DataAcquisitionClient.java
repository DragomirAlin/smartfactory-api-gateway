package ro.dragomiralin.gateway.client;

import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.dragomiralin.gateway.client.dto.Data;
import ro.dragomiralin.gateway.client.dto.Message;

import java.util.List;
import java.util.NoSuchElementException;

import static ro.dragomiralin.gateway.client.CoreClientConstants.CORE;

@FeignClient(name = "acquisition-data-mqtt-service")
@RibbonClient(name = "acquisition-data-mqtt-service")
public interface DataAcquisitionClient  {

    @CircuitBreaker(name = CORE, fallbackMethod = "coreFallback")
    @RateLimiter(name = CORE)
    @GetMapping(value = "/mqtt")
    String getHome();

    @CircuitBreaker(name = CORE, fallbackMethod = "coreFallback")
    @RateLimiter(name = CORE)
    @PostMapping(value = "/mqtt/publish")
    void publish(@RequestBody Message message);

    @CircuitBreaker(name = CORE, fallbackMethod = "coreFallback")
    @RateLimiter(name = CORE)
    @DeleteMapping("/mqtt/unsubscribe")
    ResponseEntity<Void> unsubscribe(@RequestParam String topic);

    @CircuitBreaker(name = CORE, fallbackMethod = "coreFallback")
    @RateLimiter(name = CORE)
    @PostMapping("/mqtt/subscribe")
    void subscribe(@RequestParam String topic);

    @CircuitBreaker(name = CORE, fallbackMethod = "coreFallback")
    @RateLimiter(name = CORE)
    @GetMapping("/mqtt/data")
    List<Data> allData();

    @CircuitBreaker(name = CORE, fallbackMethod = "coreFallback")
    @RateLimiter(name = CORE)
    @GetMapping("/mqtt/data/{topic}")
    void getDataByTopic(@PathVariable String topic);

    default ResponseEntity<Void> coreFallback(String id, CallNotPermittedException exception) {
        throw new NoSuchElementException();
    }

    default ResponseEntity<Void> coreFallback(String id, Exception exception) {
        throw new RuntimeException();
    }
}
