package by.du.petrolstation.facade;

import by.du.petrolstation.dto.PetrolDto;
import by.du.petrolstation.model.Petrol;
import by.du.petrolstation.service.DispenserService;
import by.du.petrolstation.service.PetrolService;
import by.du.petrolstation.service.TankService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PetrolFacade {

    private final DispenserService dispenserService;
    private final TankService tankService;
    private final PetrolService petrolService;

    public void deleteById(Long id) {
        final Petrol petrol = petrolService.findById(id);
        dispenserService.deleteByPetrol(petrol);
        tankService.deleteByPetrol(petrol);
        petrolService.deleteById(id);
    }

    public List<PetrolDto> findAll() {
        return petrolService.findAll();
    }

    public void add(PetrolDto petrolDto) {
        petrolService.add(petrolDto);
    }

    public PetrolDto findById(Long id) {
        return petrolService.findByIdDto(id);
    }


    public void update(PetrolDto petrolDto) {
        final Petrol updatingPetrol = petrolService.findById(petrolDto.getId());
        updatingPetrol.setName(petrolDto.getName());
        updatingPetrol.setPrice(petrolDto.getPrice());
        petrolService.update(updatingPetrol);
    }
}
