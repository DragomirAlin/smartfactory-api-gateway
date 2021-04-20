package ro.dragomiralin.gateway.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import ro.dragomiralin.gateway.client.NotificationClient;
import ro.dragomiralin.gateway.client.dto.MailDTO;
import ro.dragomiralin.gateway.client.dto.Response;
import ro.dragomiralin.gateway.client.dto.SmsDTO;

@RestController
@RequestMapping("/notification")
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationClient notificationClient;

    @GetMapping
    public Response test() {
        return Response.builder()
                .response(notificationClient.test())
                .build();
    }

    @PostMapping("/mail")
    public void sendMail(@AuthenticationPrincipal Jwt jwt, @RequestBody MailDTO mail) {
        notificationClient.sendMail(mail);
    }

    @PostMapping("/sms")
    public void sendSMS(@AuthenticationPrincipal Jwt jwt, @RequestBody SmsDTO sms) {
        notificationClient.sendSMS(sms);
    }

}
