package oleksandrcod.demo.config;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import oleksandrcod.demo.event.OrderPlacedEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration(proxyBeanMethods = false)
@RequiredArgsConstructor
public class ManualConfiguration {

    private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;

    @PostConstruct
    void setup() {
        this.kafkaTemplate.setObservationEnabled(true);
    }
}
