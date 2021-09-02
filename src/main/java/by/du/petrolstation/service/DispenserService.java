package by.du.petrolstation.service;

import by.du.petrolstation.model.Dispenser;
import by.du.petrolstation.model.Petrol;
import by.du.petrolstation.repository.DispenserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class DispenserService {

    private final DispenserRepository dispenserRepository;

    public List<Dispenser> findAll() {
        return dispenserRepository.findAll();
    }

    public Dispenser findById(Long id) {
        return dispenserRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
    }

    public Dispenser update(Dispenser dispenser) {
        return dispenserRepository.save(dispenser);
    }

    public void deleteById(Long id) {
        dispenserRepository.deleteById(id);
    }

    public void deleteByPetrol(Petrol petrol) {
        final List<Dispenser> dispensers = dispenserRepository.findAllByPetrol(petrol).stream()
                .peek(d -> d.getPetrolList().remove(petrol))
                .collect(Collectors.toList());

        dispenserRepository.saveAll(dispensers);
    }
}
