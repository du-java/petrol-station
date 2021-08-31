package by.du.petrolstation.repository;

import by.du.petrolstation.model.Station;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StationRepository extends JpaRepository<Station, Long> {
}
