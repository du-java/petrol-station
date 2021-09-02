package by.du.petrolstation.repository;

import by.du.petrolstation.model.Dispenser;
import by.du.petrolstation.model.Petrol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DispenserRepository extends JpaRepository<Dispenser, Long> {

    @Query("select d from Dispenser d where ?1 member of d.petrolList")
    List<Dispenser> findAllByPetrol(Petrol petrol);
}
