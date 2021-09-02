package by.du.petrolstation.facade;

import by.du.petrolstation.dto.PetrolDto;
import by.du.petrolstation.dto.TankDto;
import by.du.petrolstation.model.Petrol;
import by.du.petrolstation.model.Tank;
import by.du.petrolstation.service.PetrolService;
import by.du.petrolstation.service.StationService;
import by.du.petrolstation.service.TankService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TankFacade {
    private final TankService tankService;
    private final PetrolService petrolService;
    private final StationService stationService;

    public void add(TankDto tankDto) {
        final Petrol petrol = petrolService.findByName(tankDto.getPetrol());
        final Tank tank = tankService.add(tankDto, petrol);
        stationService.addTank(tank);
    }

    public List<PetrolDto> findAllPetrols() {
        return petrolService.findAll();
    }

    public List<TankDto> findAll() {
        return tankService.findAll();
    }

    public TankDto findById(Long id) {
        return tankService.findByIdDto(id);
    }

    public void update(TankDto tankDto) {
        final Petrol petrol = petrolService.findByName(tankDto.getPetrol());
        tankService.update(tankDto, petrol);
    }

    public void deleteById(Long id) {
        tankService.deleteById(id);
    }
}
