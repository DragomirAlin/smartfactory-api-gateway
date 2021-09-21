package ro.dragomiralin.gateway.client;

import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.dragomiralin.api.model.CreatedDTO;
import ro.dragomiralin.api.model.DataDTO;
import ro.dragomiralin.api.specification.AcquisitionApi;
import ro.dragomiralin.gateway.client.dto.Data;
import ro.dragomiralin.gateway.client.dto.Message;
import ro.dragomiralin.gateway.client.dto.PaginationResponse;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import static ro.dragomiralin.gateway.client.CoreClientConstants.CORE;

@FeignClient(name = "acquisition-service")
@RibbonClient(name = "acquisition-service")
public interface DataAcquisitionClient {

    //    @CircuitBreaker(name = CORE)
//    @RateLimiter(name = CORE)
    @GetMapping(value = "/mqtt")
    ResponseEntity<String> getHome();

    //    @CircuitBreaker(name = CORE, fallbackMethod = "coreFallback")
//    @RateLimiter(name = CORE)
    @PostMapping(value = "/mqtt/publish")
    ResponseEntity<Void> publish(@RequestBody Message message);

    //    @CircuitBreaker(name = CORE, fallbackMethod = "coreFallback")
//    @RateLimiter(name = CORE)
    @DeleteMapping("/mqtt/unsubscribe")
    ResponseEntity<Void> unsubscribe(@RequestParam String topic);

    //    @CircuitBreaker(name = CORE, fallbackMethod = "coreFallback")
//    @RateLimiter(name = CORE)
    @PostMapping("/mqtt/subscribe")
    ResponseEntity<Void> subscribe(@RequestParam String topic);

    //    @CircuitBreaker(name = CORE, fallbackMethod = "coreFallback")
//    @RateLimiter(name = CORE)
    @GetMapping("/mqtt/data")
    ResponseEntity<PaginationResponse> allData(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "25") int size);

    //    @CircuitBreaker(name = CORE, fallbackMethod = "coreFallback")
//    @RateLimiter(name = CORE)
    @GetMapping("/mqtt/data/{topic}")
    ResponseEntity<PaginationResponse> getDataByTopic(@PathVariable String topic, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "25") int size);

    @PostMapping
    ResponseEntity<PaginationResponse> searchData(@RequestBody Map<String, Object> params, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "25") int size);

//    default String coreFallback(String id, CallNotPermittedException exception) {
//        throw new NoSuchElementException();
//    }
//
//    default String coreFallback(String id, Exception exception) {
//        throw new RuntimeException();
//    }
}
