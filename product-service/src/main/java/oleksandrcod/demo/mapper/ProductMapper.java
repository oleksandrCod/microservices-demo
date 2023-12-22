package oleksandrcod.demo.mapper;

import oleksandrcod.demo.config.MapperConfig;
import oleksandrcod.demo.dto.CreateProductRequestDto;
import oleksandrcod.demo.dto.ProductResponseDto;
import oleksandrcod.demo.model.Product;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface ProductMapper {
    Product toModel(CreateProductRequestDto requestDto);

    ProductResponseDto toDto(Product product);

}
