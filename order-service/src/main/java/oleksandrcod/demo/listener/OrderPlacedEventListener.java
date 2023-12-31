package oleksandrcod.demo.listener;

import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import oleksandrcod.demo.event.OrderPlacedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderPlacedEventListener {

    private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;
    private final ObservationRegistry observationRegistry;

    @EventListener
    public void handleOrderPlacedEvent(OrderPlacedEvent event) {
        log.info("Order Placed Event Received,"
                + " Sending OrderPlacedEvent to notificationTopic: {}", event.getOrderNumber());

        // Create Observation for Kafka Template
        try {
            Observation
                    .createNotStarted("notification-topic", this.observationRegistry)
                    .observeChecked(() -> {
                                CompletableFuture<SendResult<String, OrderPlacedEvent>> future =
                                        kafkaTemplate.send("notificationTopic",
                                                new OrderPlacedEvent(event.getOrderNumber()));
                                return future.handle(
                                        (result, throwable) -> CompletableFuture
                                                .completedFuture(result));
                    }
                    ).get();
        } catch (InterruptedException | ExecutionException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Error while sending message to Kafka", e);
        }
    }
}
