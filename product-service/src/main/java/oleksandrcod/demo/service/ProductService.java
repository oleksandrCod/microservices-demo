package oleksandrcod.demo.service;

import java.util.List;
import oleksandrcod.demo.dto.CreateProductRequestDto;
import oleksandrcod.demo.dto.ProductResponseDto;

public interface ProductService {
    ProductResponseDto createProduct(CreateProductRequestDto requestDto);

    void deleteProduct(Long id);

    List<ProductResponseDto> getAll();
}
