package by.du.petrolstation.service;

import by.du.petrolstation.exception.NotFoundException;
import by.du.petrolstation.model.Order;
import by.du.petrolstation.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public Order getById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Order update(Order updated) {
        return orderRepository.save(updated);
    }
}
