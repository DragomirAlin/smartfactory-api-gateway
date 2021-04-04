package ro.dragomiralin.gateway.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import ro.dragomiralin.gateway.client.DataAcquisitionClient;
import ro.dragomiralin.gateway.client.dto.Message;

@RestController
@RequestMapping("/mqtt")
@RequiredArgsConstructor
public class AcquisitionController {
    private final DataAcquisitionClient dataAcquisitionClient;

    @GetMapping
    public String getMqtt(@AuthenticationPrincipal Jwt principal) {
        return dataAcquisitionClient.getHome();
    }

    @PostMapping("/publish")
    public void publish(@AuthenticationPrincipal Jwt principal, @RequestBody Message message){
        dataAcquisitionClient.publish(message);
    }

    @DeleteMapping("/unsubscribe")
    public void unsubscribe(@AuthenticationPrincipal Jwt principal, @RequestParam String topic) {
        dataAcquisitionClient.unsubscribe(topic);
    }

    @PostMapping("/subscribe")
    public void subscribe(@AuthenticationPrincipal Jwt principal, @RequestParam String topic) {
        dataAcquisitionClient.subscribe(topic);
    }
}
