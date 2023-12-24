package oleksandrcod.demo.service.impl;

import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import oleksandrcod.demo.dto.InventoryResponseDto;
import oleksandrcod.demo.dto.OrderRequestDto;
import oleksandrcod.demo.event.OrderPlacedEvent;
import oleksandrcod.demo.mapper.OrderMapper;
import oleksandrcod.demo.model.Order;
import oleksandrcod.demo.model.OrderItems;
import oleksandrcod.demo.repository.OrderRepository;
import oleksandrcod.demo.service.OrderService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {

    private final WebClient.Builder webClientBuilder;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final ObservationRegistry observationRegistry;

    @Override
    public String placeOrder(OrderRequestDto requestDto) {
        Order order = orderMapper.toModel(requestDto);
        order.setOrderNumber(UUID.randomUUID().toString());

        List<String> skuCodes = order.getOrderItems().stream()
                .map(OrderItems::getSkuCode)
                .toList();

        Observation inventoryServiceObservation =
                Observation.createNotStarted("inventory-service-lookup",
                        this.observationRegistry);
        inventoryServiceObservation.lowCardinalityKeyValue("call", "inventory-service");
        return inventoryServiceObservation.observe(() -> {
            InventoryResponseDto[] inventoryResponseArray = webClientBuilder.build().get()
                    .uri("http://inventory-service/api/inventory",
                            uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                    .retrieve()
                    .bodyToMono(InventoryResponseDto[].class)
                    .block();

            boolean allProductsInStock = Arrays.stream(inventoryResponseArray)
                    .allMatch(InventoryResponseDto::isInStock);

            if (allProductsInStock) {
                orderRepository.save(order);
                // publish Order Placed Event
                applicationEventPublisher.publishEvent(new OrderPlacedEvent(
                        this, order.getOrderNumber()));
                return "Order Placed";
            } else {
                throw new IllegalArgumentException("Product is not in stock");
            }
        });
    }
}
