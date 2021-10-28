package ro.dragomiralin.gateway.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Service
@Configuration
public class RabbitMQConfiguration {
    @Value("${smartfactory.rabbitmq.mqtt.acquisition.queue}")
    private String gatewayQueue;
    @Value("${smartfactory.rabbitmq.mqtt.acquisition.routingkey}")
    private String gatewayRoutingkey;
    @Value("${smartfactory.rabbitmq.mqtt.acquisition.exchange}")
    private String mqttExchange;

    @Bean
    Queue queue() {
        return new Queue(gatewayQueue, false);
    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange(mqttExchange);
    }

    @Bean
    Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(gatewayRoutingkey);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        final var rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}