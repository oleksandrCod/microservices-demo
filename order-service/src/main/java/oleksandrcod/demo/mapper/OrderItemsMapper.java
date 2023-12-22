package oleksandrcod.demo.mapper;

import oleksandrcod.demo.config.MapperConfig;
import oleksandrcod.demo.dto.OrderItemsDto;
import oleksandrcod.demo.model.OrderItems;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface OrderItemsMapper {
    OrderItems toModel(OrderItemsDto orderItemsDto);
}
