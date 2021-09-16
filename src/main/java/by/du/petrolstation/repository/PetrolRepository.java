package by.du.petrolstation.repository;

import by.du.petrolstation.model.Petrol;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
public interface PetrolRepository extends JpaRepository<Petrol, Long> {
    Optional<Petrol> findByName(String name);

    }
