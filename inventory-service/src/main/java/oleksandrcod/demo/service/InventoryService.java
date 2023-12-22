package oleksandrcod.demo.service;

import java.util.List;
import oleksandrcod.demo.dto.InventoryResponseDto;

public interface InventoryService {
    public List<InventoryResponseDto> isInStock(List<String> skuCode);
}
