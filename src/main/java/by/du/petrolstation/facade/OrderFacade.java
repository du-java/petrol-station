package by.du.petrolstation.facade;

import by.du.petrolstation.dto.DispenserDto;
import by.du.petrolstation.dto.OrderDto;
import by.du.petrolstation.model.Order;
import by.du.petrolstation.model.Petrol;
import by.du.petrolstation.repository.OrderRepository;
import by.du.petrolstation.service.DispenserService;
import by.du.petrolstation.service.PetrolService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderFacade {
    private final DispenserService dispenserService;
    private final PetrolService petrolService;
    private final OrderRepository orderRepository;

    public List<DispenserDto> getAllDispensers() {
        return dispenserService.findAll();
    }

    public DispenserDto getDispenser(Long id) {
        return dispenserService.findByIdDto(id);
    }

    public OrderDto getOrderByAmount(BigDecimal amount, String petrolName) {
        Petrol petrol = petrolService.findByName(petrolName);
        BigDecimal quantity = amount.divide(petrol.getPrice());
        return OrderDto.builder()
                .amount(amount)
                .petrol(petrol.getName())
                .quantity(quantity)
                .price(petrol.getPrice())
                .build();

    }

    public OrderDto getOrderByQuantity(BigDecimal quantity, String petrolName) {
        Petrol petrol = petrolService.findByName(petrolName);
        BigDecimal amount = quantity.multiply(petrol.getPrice());
        return OrderDto.builder()
                .amount(amount)
                .petrol(petrol.getName())
                .quantity(quantity)
                .price(petrol.getPrice())
                .build();

    }

    public void add(OrderDto orderDto) {
        Order order = Order.builder()
                .quantity(orderDto.getQuantity())
                .petrol(petrolService.findByName(orderDto.getPetrol()))
                .amount(orderDto.getAmount())
                .build();
        orderRepository.save(order);
    }
}
