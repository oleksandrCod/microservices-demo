package oleksandrcod.demo.dto;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class OrderItemsDto {
    private Long id;
    private String skuCode;
    private BigDecimal price;
    private Integer quantity;
}
