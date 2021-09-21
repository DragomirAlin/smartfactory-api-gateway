package ro.dragomiralin.gateway.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import ro.dragomiralin.gateway.client.DataAcquisitionClient;
import ro.dragomiralin.gateway.client.dto.Message;
import ro.dragomiralin.gateway.client.dto.PaginationResponse;
import ro.dragomiralin.gateway.client.dto.Response;

import java.util.Map;

@RestController
@RequestMapping("/acquisition")
@RequiredArgsConstructor
public class AcquisitionController {
    private final DataAcquisitionClient dataAcquisitionClient;

    @GetMapping
    public ResponseEntity<Response> getMqtt(@AuthenticationPrincipal Jwt principal) {
        return new ResponseEntity<>(Response.builder()
                .response(dataAcquisitionClient.getHome().getBody())
                .build(), HttpStatus.ACCEPTED);
    }

    @PostMapping("/publish")
    public ResponseEntity<Void> publish(@AuthenticationPrincipal Jwt principal, @RequestBody Message message) {
        dataAcquisitionClient.publish(message);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/unsubscribe")
    public ResponseEntity<Void> unsubscribe(@AuthenticationPrincipal Jwt principal, @RequestParam String topic) {
        dataAcquisitionClient.unsubscribe(topic);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PostMapping("/subscribe")
    public ResponseEntity<Void> subscribe(@AuthenticationPrincipal Jwt principal, @RequestParam String topic) {
        dataAcquisitionClient.subscribe(topic);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("/data")
    public ResponseEntity<PaginationResponse> allData(@AuthenticationPrincipal Jwt principal, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "25") int size) {
        return dataAcquisitionClient.allData(page, size);
    }

    @GetMapping("/data/{topic}")
    public ResponseEntity<PaginationResponse> getDataByTopic(@AuthenticationPrincipal Jwt principal, @PathVariable String topic, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "25") int size) {
        return dataAcquisitionClient.getDataByTopic(topic, page, size);
    }

    @PostMapping("/data/search")
    public ResponseEntity<PaginationResponse> searchData(@RequestBody Map<String, Object> params, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "25") int size) {
        return dataAcquisitionClient.searchData(params, page, size);
    }
}
