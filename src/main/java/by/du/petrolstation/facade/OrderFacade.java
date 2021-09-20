package by.du.petrolstation.facade;

import by.du.petrolstation.dto.DispenserDto;
import by.du.petrolstation.dto.OrderDto;
import by.du.petrolstation.model.Order;
import by.du.petrolstation.model.Petrol;
import by.du.petrolstation.model.User;
import by.du.petrolstation.repository.OrderRepository;
import by.du.petrolstation.repository.UserRepository;
import by.du.petrolstation.service.DispenserService;
import by.du.petrolstation.service.PetrolService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderFacade {
    private final DispenserService dispenserService;
    private final PetrolService petrolService;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public List<DispenserDto> getAllDispensers() {
        return dispenserService.findAll();
    }

    public DispenserDto getDispenser(Long id) {
        return dispenserService.findByIdDto(id);
    }

    public OrderDto getOrderByAmount(BigDecimal amount, String petrolName) {
        Petrol petrol = petrolService.findByName(petrolName);
        BigDecimal quantity = amount.divide(petrol.getPrice(), 2, RoundingMode.HALF_UP);
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

    public void add(OrderDto orderDto, Authentication authentication) {
        Order order = Order.builder()
                .quantity(orderDto.getQuantity())
                .petrol(petrolService.findByName(orderDto.getPetrol()))
                .amount(orderDto.getAmount())
                .user(getUser(authentication))
                .build();
        orderRepository.save(order);
    }

    public List<OrderDto> findAll(Authentication authentication) {
        return orderRepository.findAllByUser(getUser(authentication)).stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }

    public User getUser(Authentication authentication) {
        return userRepository.findByName(authentication.getName())
                .orElseThrow(() -> new IllegalArgumentException("user not found"));

    }

    private OrderDto convert(Order order) {
        return OrderDto.builder()
                .id(order.getId())
                .petrol(order.getPetrol().getName())
                .amount(order.getAmount())
                .quantity(order.getQuantity())
                .price(order.getPetrol().getPrice())
                .user(order.getUser())
                .build();
    }

    public List<OrderDto> findAll() {
        return orderRepository.findAll().stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }

    public OrderDto findById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("order not found"));
        return convert(order);
    }

    public void deleteById(Long id) {
        orderRepository.deleteById(id);
    }
}
