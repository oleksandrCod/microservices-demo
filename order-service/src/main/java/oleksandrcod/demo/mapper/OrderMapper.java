package oleksandrcod.demo.mapper;

import oleksandrcod.demo.config.MapperConfig;
import oleksandrcod.demo.dto.OrderRequestDto;
import oleksandrcod.demo.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface OrderMapper {
    @Mapping(source = "orderItemsDto", target = "orderItems")
    Order toModel(OrderRequestDto requestDto);

}
