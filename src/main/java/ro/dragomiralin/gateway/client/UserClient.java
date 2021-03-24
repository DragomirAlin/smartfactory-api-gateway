package ro.dragomiralin.gateway.client;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import ro.dragomiralin.gateway.client.dto.User;

@FeignClient(name="user-management-service")
@RibbonClient(name = "user-management-service")
public interface UserClient {

    @GetMapping(value = "/user")
    User getUser();
}
