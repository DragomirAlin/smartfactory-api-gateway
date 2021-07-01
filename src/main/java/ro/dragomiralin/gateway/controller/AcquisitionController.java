package ro.dragomiralin.gateway.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import ro.dragomiralin.gateway.client.DataAcquisitionClient;
import ro.dragomiralin.gateway.client.dto.Data;
import ro.dragomiralin.gateway.client.dto.Message;
import ro.dragomiralin.gateway.client.dto.Response;

import java.util.List;

@RestController
@RequestMapping("/acquisition")
@RequiredArgsConstructor
public class AcquisitionController {
    private final DataAcquisitionClient dataAcquisitionClient;

    @GetMapping
    public Response getMqtt(@AuthenticationPrincipal Jwt principal) {
        return Response.builder()
                .response(dataAcquisitionClient.getHome())
                .build();
    }

    @PostMapping("/publish")
    public void publish(@AuthenticationPrincipal Jwt principal, @RequestBody Message message) {
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

    @GetMapping("/data")
    public List<Data> allData(@AuthenticationPrincipal Jwt principal) {
        return dataAcquisitionClient.allData();
    }

    @GetMapping("/data/{topic}")
    public List<Data> getDataByTopic(@AuthenticationPrincipal Jwt principal, @PathVariable String topic) {
        return dataAcquisitionClient.getDataByTopic(topic);
    }


}
