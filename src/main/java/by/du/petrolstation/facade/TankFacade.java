package by.du.petrolstation.facade;

import by.du.petrolstation.dto.TankDto;
import by.du.petrolstation.model.Order;
import by.du.petrolstation.model.Petrol;
import by.du.petrolstation.model.Tank;
import by.du.petrolstation.service.PetrolService;

import by.du.petrolstation.service.TankService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TankFacade {
    private final TankService tankService;
    private final PetrolService petrolService;

    public void add(TankDto tankDto) {
        final Petrol petrol = petrolService.findByName(tankDto.getPetrol());
        tankService.add(tankDto, petrol);
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

    public void soldPetrol(Order order) {
        Petrol petrol = order.getPetrol();
        Tank tank = tankService.findByPetrol(petrol);
            BigDecimal subtractQuantity = tank.getQuantity().subtract(order.getQuantity());
            tank.setQuantity(subtractQuantity);
        TankDto tankDto = tankService.converter(tank);
        tankService.update(tankDto, petrol);
    }
}
