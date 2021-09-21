package by.du.petrolstation.service.converter;

import by.du.petrolstation.dto.OrderDto;
import by.du.petrolstation.model.Order;
import by.du.petrolstation.service.PetrolService;
import by.du.petrolstation.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderConverter implements Converter<OrderDto, Order> {

    private final PetrolService petrolService;
    private final UserService userService;

    @Override
    public Order convert(OrderDto dto) {
        return Order.builder()
                .id(dto.getId())
                .petrol(petrolService.findByName(dto.getPetrol()))
                .quantity(dto.getQuantity())
//                .user(userService.findByName(dto.getUser()))
                .amount(dto.getAmount())
                .build();
    }

    @Override
    public OrderDto convert(Order model) {
        return OrderDto.builder()
                .id(model.getId())
                .petrol(model.getPetrol().getName())
                .quantity(model.getQuantity())
//                .user(model.getUser().getName())
                .amount(model.getAmount())
                .price(model.getPetrol().getPrice())
                .build();
    }
}

