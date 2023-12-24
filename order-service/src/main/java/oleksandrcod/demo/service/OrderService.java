package oleksandrcod.demo.service;

import oleksandrcod.demo.dto.OrderRequestDto;

public interface OrderService {
    public String placeOrder(OrderRequestDto requestDto);
}
