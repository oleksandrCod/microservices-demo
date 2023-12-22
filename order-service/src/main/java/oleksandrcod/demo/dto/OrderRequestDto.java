package oleksandrcod.demo.dto;

import java.util.List;
import lombok.Data;

@Data
public class OrderRequestDto {

    private List<OrderItemsDto> orderItemsDto;
}
