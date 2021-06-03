package ro.dragomiralin.gateway.client.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Email;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Subscription {
    private String id;
    private String macAddress;
    private AcqusitionType acquisitionType;
    private DeviceType device;
    private Object customMonitor;
    private NotificationDetails notificationDetails;

    public enum Operator {
        LESS, EQUALTO, GREATHER, None
    }

    @Data
    @Builder
    public static class NotificationDetails {
        private boolean sendEmail;
        private boolean sendSMS;
        @Email
        private String email;
        private String phoneNumber;

    }

    @Data
    @Builder
    public static class Dth22 {
        private double temperatureValue;
        private Operator temperatureOperator;
        private double humidityValue;
        private Operator humidityOperator;

    }

    @Data
    @Builder
    public static class Mq135 {
        private double value;
        private Operator operator;
    }

    @Data
    @Builder
    public static class GoogleMini {
        private String message;
    }
}
