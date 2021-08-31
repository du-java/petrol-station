package by.du.petrolstation.repository;

import by.du.petrolstation.model.Tank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TankRepository extends JpaRepository<Tank, Long> {
}
