package oleksandrcod.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import oleksandrcod.demo.dto.CreateProductRequestDto;
import oleksandrcod.demo.dto.ProductResponseDto;
import oleksandrcod.demo.mapper.ProductMapper;
import oleksandrcod.demo.model.Product;
import oleksandrcod.demo.repository.ProductRepository;
import oleksandrcod.demo.service.ProductService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public ProductResponseDto createProduct(CreateProductRequestDto requestDto) {
        Product product = productMapper.toModel(requestDto);
        return productMapper.toDto(productRepository.save(product));
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductResponseDto> getAll() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }
}
