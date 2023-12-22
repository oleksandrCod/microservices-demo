package oleksandrcod.demo.service.impl;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import oleksandrcod.demo.dto.OrderRequestDto;
import oleksandrcod.demo.mapper.OrderMapper;
import oleksandrcod.demo.model.Order;
import oleksandrcod.demo.repository.OrderRepository;
import oleksandrcod.demo.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Override
    public void placeOrder(OrderRequestDto requestDto) {
        Order order = orderMapper.toModel(requestDto);
        order.setOrderNumber(UUID.randomUUID().toString());
        orderRepository.save(order);

    }
}
