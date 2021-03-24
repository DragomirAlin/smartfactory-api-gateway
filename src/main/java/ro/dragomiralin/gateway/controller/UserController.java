package ro.dragomiralin.gateway.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.dragomiralin.gateway.client.DataAcquisitionClient;
import ro.dragomiralin.gateway.client.UserClient;
import ro.dragomiralin.gateway.client.dto.User;


@RestController
@RequestMapping("/api-v1")
@RequiredArgsConstructor
public class UserController {
    private final UserClient userClient;
    private final DataAcquisitionClient dataAcquisitionClient;

    @GetMapping("/user")
    public User getUser() {
        return userClient.getUser();
    }

    @GetMapping("/mqtt")
    public String getMqtt() {
        return dataAcquisitionClient.getHome();
    }
}
