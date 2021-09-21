package by.du.petrolstation.service.impl;

import by.du.petrolstation.dto.OrderDto;
import by.du.petrolstation.model.Order;
import by.du.petrolstation.repository.OrderRepository;
import by.du.petrolstation.service.AbstractRestService;
import by.du.petrolstation.service.OrderService;
import by.du.petrolstation.service.converter.OrderConverter;
import org.springframework.stereotype.Service;

@Service
public class OrderRestService extends AbstractRestService<OrderDto, Order> {

    private final OrderService orderService;
    private final OrderConverter orderConverter;

    public OrderRestService(OrderRepository orderRepository, OrderConverter orderConverter, OrderService orderService) {
        super(orderRepository, orderConverter);
        this.orderService = orderService;
        this.orderConverter = orderConverter;
    }

    public OrderDto patch(OrderDto dto) {
        final Order old = orderService.getById(dto.getId());
        old.setQuantity(dto.getQuantity());
        old.setAmount(old.getQuantity().multiply(old.getPetrol().getPrice()));
        final Order saved = orderService.update(old);
        return orderConverter.convert(saved);
    }
}
