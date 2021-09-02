package by.du.petrolstation.repository;

import by.du.petrolstation.model.Petrol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PetrolRepository extends JpaRepository<Petrol, Long> {

    Optional<Petrol> findByName(String name);
}
