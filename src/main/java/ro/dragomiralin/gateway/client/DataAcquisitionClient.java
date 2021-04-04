package ro.dragomiralin.gateway.client;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import ro.dragomiralin.gateway.client.dto.Message;

@FeignClient(name = "acquisition-data-mqtt-service")
@RibbonClient(name = "acquisition-data-mqtt-service")
public interface DataAcquisitionClient {

    @GetMapping(value = "/mqtt")
    String getHome();

    @PostMapping(value = "/mqtt/publish")
    void publish(@RequestBody Message message);

    @DeleteMapping("/mqtt/unsubscribe")
    void unsubscribe(@RequestParam String topic);

    @PostMapping("/mqtt/subscribe")
    void subscribe(@RequestParam String topic);
}
