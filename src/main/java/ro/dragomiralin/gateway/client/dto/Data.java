package ro.dragomiralin.gateway.client.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@lombok.Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Data {
    private String id;
    private String topic;
    private Object payload;
    private Date arriveAt;
}
