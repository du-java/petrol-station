package by.du.petrolstation.repository;

import by.du.petrolstation.model.Petrol;
import by.du.petrolstation.model.Tank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface TankRepository extends JpaRepository<Tank, Long> {

    void deleteAllByPetrol(Petrol petrol);

    List<Tank> findAllByPetrol(Petrol petrol);

    Tank findByPetrol(Petrol petrol);
}
