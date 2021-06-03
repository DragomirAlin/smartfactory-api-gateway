package ro.dragomiralin.gateway.configuration;

import feign.RequestInterceptor;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import ro.dragomiralin.gateway.client.DataAcquisitionClient;
import ro.dragomiralin.gateway.client.NotificationClient;
import ro.dragomiralin.gateway.client.SubscriptionClient;
import ro.dragomiralin.gateway.client.UserClient;

@Configuration
@EnableFeignClients(basePackageClasses = {UserClient.class, DataAcquisitionClient.class, NotificationClient.class, SubscriptionClient.class})
public class FeignConfiguration {

    @Bean
    public RequestInterceptor authRequestInterceptor() {
        return requestTemplate -> {
            var jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            requestTemplate.header("Authorization", String.format("Bearer %s", jwt.getTokenValue()));
        };
    }
}