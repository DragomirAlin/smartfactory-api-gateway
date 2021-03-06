package ro.dragomiralin.gateway.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.dragomiralin.gateway.client.UserClient;
import ro.dragomiralin.gateway.client.dto.User;

@RestController
@RequestMapping("/api-v1")
@RequiredArgsConstructor
public class UserController {
    private final UserClient userClient;

    @GetMapping("/user")
    public User getUset(){
        return userClient.getUser();
    }
}
