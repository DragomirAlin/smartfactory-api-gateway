package ro.dragomiralin.gateway.client;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "acquisition-data-mqtt-service")
@RibbonClient(name = "acquisition-data-mqtt-service")
public interface DataAcquisitionClient {

    @GetMapping(value = "/mqtt")
    String getHome();
}
