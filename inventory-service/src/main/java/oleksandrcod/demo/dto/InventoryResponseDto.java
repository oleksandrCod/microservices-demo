package oleksandrcod.demo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InventoryResponseDto {
    private String skuCode;
    private boolean isInStock;
}
