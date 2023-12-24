package oleksandrcod.demo.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import oleksandrcod.demo.dto.InventoryResponseDto;
import oleksandrcod.demo.dto.OrderRequestDto;
import oleksandrcod.demo.mapper.OrderMapper;
import oleksandrcod.demo.model.Order;
import oleksandrcod.demo.model.OrderItems;
import oleksandrcod.demo.repository.OrderRepository;
import oleksandrcod.demo.service.OrderService;
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

    @Override
    public String placeOrder(OrderRequestDto requestDto) {
        Order order = orderMapper.toModel(requestDto);
        order.setOrderNumber(UUID.randomUUID().toString());

        List<String> skuCodes = order.getOrderItems().stream()
                .map(OrderItems::getSkuCode)
                .toList();

        InventoryResponseDto[] inventoryResponseDto = webClientBuilder.build().get()
                .uri("http://inventory-service/api/inventory",
                        uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponseDto[].class)
                .block();

        boolean allProductsInStock = Arrays.stream(inventoryResponseDto)
                .allMatch(InventoryResponseDto::isInStock);

        if (allProductsInStock) {
            orderRepository.save(order);
            return "Order placed successfully.";
        } else {
            throw new IllegalArgumentException("Product is not in stock.");
        }
    }
}
