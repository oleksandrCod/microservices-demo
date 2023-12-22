package oleksandrcod.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import oleksandrcod.demo.dto.InventoryResponseDto;
import oleksandrcod.demo.repository.InventoryRepository;
import oleksandrcod.demo.service.InventoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {
    private final InventoryRepository inventoryRepository;

    @Override
    @Transactional(readOnly = true)
    public List<InventoryResponseDto> isInStock(List<String> skuCode) {
        return inventoryRepository
                .findBySkuCodeIn(skuCode).stream()
                .map(inventory ->
                        InventoryResponseDto.builder()
                                .skuCode(inventory.getSkuCode())
                                .isInStock(inventory.getQuantity() > 0)
                                .build()
                ).collect(Collectors.toList());
    }
}
