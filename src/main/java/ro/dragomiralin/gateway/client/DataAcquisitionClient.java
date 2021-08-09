package ro.dragomiralin.gateway.client;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.agilehub.javacourse.car.hire.api.model.CreatedDTO;
import ro.agilehub.javacourse.car.hire.api.model.DataDTO;
import ro.agilehub.javacourse.car.hire.api.specification.AcquisitionApi;
import ro.dragomiralin.gateway.client.dto.Data;
import ro.dragomiralin.gateway.client.dto.Message;

import java.util.List;

@FeignClient(name = "acquisition-data-mqtt-service")
@RibbonClient(name = "acquisition-data-mqtt-service")
public interface DataAcquisitionClient extends AcquisitionApi {

    @GetMapping(value = "/mqtt")
    String getHome();

    @PostMapping(value = "/mqtt/publish")
    void publish(@RequestBody Message message);

    @DeleteMapping("/mqtt/unsubscribe")
    ResponseEntity<Void> unsubscribe(@RequestParam String topic);

    @PostMapping("/mqtt/subscribe")
    ResponseEntity<CreatedDTO> subscribe(@RequestParam String topic);

    @GetMapping("/mqtt/data")
    List<Data> allData();

    @GetMapping("/mqtt/data/{topic}")
    ResponseEntity<List<DataDTO>> getDataByTopic(@PathVariable String topic);
}
