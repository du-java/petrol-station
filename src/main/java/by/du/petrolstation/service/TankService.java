package by.du.petrolstation.service;

import by.du.petrolstation.dto.TankDto;
import by.du.petrolstation.model.Petrol;
import by.du.petrolstation.model.Tank;
import by.du.petrolstation.repository.TankRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class TankService {

    private final TankRepository tankRepository;
    private final StationService stationService;

    public void deleteByPetrol(Petrol petrol) {
        // remove tanks from station
        final List<Tank> tanks = findAllByPetrol(petrol);
        stationService.deleteTanks(tanks);

        deleteAll(tanks);
    }

    public List<TankDto> findAll() {
        return tankRepository.findAll().stream()
                .map(this::converter)
                .collect(Collectors.toList());
    }

    public Tank findById(Long id) {
        return tankRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
    }

    public void update(TankDto tankDto, Petrol petrol) {
        final Tank updatingTank = findById(tankDto.getId());
        updatingTank.setPetrol(petrol);
        updatingTank.setQuantity(tankDto.getQuantity());
        tankRepository.save(updatingTank);
    }

    public void deleteById(Long id) {
        tankRepository.deleteById(id);
    }

    public Tank add(TankDto tankDto, Petrol petrol) {
        final Tank tank = Tank.builder()
                .petrol(petrol)
                .quantity(tankDto.getQuantity())
                .build();
        return tankRepository.save(tank);
    }

    public void deleteAll(List<Tank> tanks) {
        tankRepository.deleteAll(tanks);
    }

    public List<Tank> findAllByPetrol(Petrol petrol) {
        return tankRepository.findAllByPetrol(petrol);
    }

    private TankDto converter(Tank tank) {
        return TankDto.builder()
                .id(tank.getId())
                .petrol(tank.getPetrol().getName())
                .quantity(tank.getQuantity())
                .build();
    }

    public TankDto findByIdDto(Long id) {
        return converter(findById(id));
    }
}
