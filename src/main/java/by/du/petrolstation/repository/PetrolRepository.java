package by.du.petrolstation.repository;

import by.du.petrolstation.model.Petrol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetrolRepository extends JpaRepository<Petrol, Long> {
}
