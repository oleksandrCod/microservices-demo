package oleksandrcod.demo.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import oleksandrcod.demo.dto.CreateProductRequestDto;
import oleksandrcod.demo.dto.ProductResponseDto;
import oleksandrcod.demo.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponseDto createProduct(@RequestBody CreateProductRequestDto requestDto) {
        return productService.createProduct(requestDto);
    }

    @GetMapping
    public List<ProductResponseDto> getAllProducts() {
        return productService.getAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}
