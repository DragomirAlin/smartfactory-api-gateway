package ro.dragomiralin.gateway.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.dragomiralin.gateway.client.dto.User;

import java.util.Collections;
import java.util.Hashtable;
import java.util.Map;

//@RestController
//@RequestMapping("/api-v1")
//@RequiredArgsConstructor
//public class UserController {
//    private final UserClient userClient;
//
//    @GetMapping("/user")
//    public User getUset(){
//        return userClient.getUser();
//    }
//
//    @GetMapping("/user/info/custom")
//    public Map<String, Object> getUserInfo(@AuthenticationPrincipal Jwt principal) {
//        return Collections.singletonMap("DOB", principal.getClaimAsString("DOB"));
//    }
//
//    @CrossOrigin(origins = "http://localhost:8084")
//    @GetMapping("/user/info")
//    public Map<String, Object> getUserInfo2(@AuthenticationPrincipal Jwt principal) {
//        Map<String, String> map = new Hashtable<String, String>();
//        map.put("user_name", principal.getClaimAsString("preferred_username"));
//        map.put("organization", principal.getClaimAsString("organization"));
//        return Collections.unmodifiableMap(map);
//    }
//}
