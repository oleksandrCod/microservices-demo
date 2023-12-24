package oleksandrcod.demo.config;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import oleksandrcod.demo.event.OrderPlacedEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;

@Configuration(proxyBeanMethods = false)
@RequiredArgsConstructor
public class ManualConfiguration {

    private final ConcurrentKafkaListenerContainerFactory<String, OrderPlacedEvent> factory;

    @PostConstruct
    void setup() {
        this.factory.getContainerProperties().setObservationEnabled(true);
    }
}
