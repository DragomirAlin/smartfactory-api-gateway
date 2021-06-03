package ro.dragomiralin.gateway.client;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import ro.dragomiralin.gateway.client.dto.Subscription;

import java.util.List;

@FeignClient(name = "subscription-service")
@RibbonClient(name = "subscription-service")
public interface SubscriptionClient {

    @PostMapping("/subscription")
    Subscription addSubscription(@RequestBody Subscription subscription);

    @GetMapping("/subscription/all")
    List<Subscription> getAllSubscriptions();

    @GetMapping("/subscription/{id}")
    Subscription getSubscription(@PathVariable String id);

    @DeleteMapping("/subscription/{id}")
    void deleteSubscription(@PathVariable String id);
}
