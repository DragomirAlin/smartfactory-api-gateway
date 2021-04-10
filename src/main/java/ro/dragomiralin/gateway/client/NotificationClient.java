package ro.dragomiralin.gateway.client;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ro.dragomiralin.gateway.client.dto.MailDTO;
import ro.dragomiralin.gateway.client.dto.SmsDTO;

@FeignClient(name = "notification-service")
@RibbonClient(name = "notification-service")
public interface NotificationClient {

    @GetMapping
   String test();

    @PostMapping("/mail")
    void sendMail(@RequestBody MailDTO mail);


    @PostMapping("/sms")
    void sendSMS(@RequestBody SmsDTO sms);
}
