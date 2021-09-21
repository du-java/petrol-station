package by.du.petrolstation.repository;

import by.du.petrolstation.model.Order;
import by.du.petrolstation.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByUser(User user);
}
