package ro.dragomiralin.gateway.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.dragomiralin.gateway.client.UserClient;
import ro.dragomiralin.gateway.client.dto.User;


@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserClient userClient;

    @GetMapping("/user")
    public User getUser() {
        return userClient.getUser();
    }

}
