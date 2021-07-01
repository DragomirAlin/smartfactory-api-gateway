package ro.dragomiralin.gateway.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.dragomiralin.gateway.client.SubscriptionClient;
import ro.dragomiralin.gateway.client.dto.Subscription;

import java.util.List;

@RestController
@RequestMapping("/subscription")
@RequiredArgsConstructor
public class SubscriptionController {
    private final SubscriptionClient subscriptionClient;

    @PostMapping()
    public Subscription addMonitor(@RequestBody Subscription subscription) {
        return subscriptionClient.addSubscription(subscription);
    }

    @GetMapping("/all")
    public List<Subscription> getAll() {
        return subscriptionClient.getAllSubscriptions();
    }

    @GetMapping("/{id}")
    public Subscription getMonitor(@PathVariable String id) {
        return subscriptionClient.getSubscription(id);
    }

    @DeleteMapping("/{id}")
    public void deleteMonitor(@PathVariable String id) {
        subscriptionClient.deleteSubscription(id);
    }
}
