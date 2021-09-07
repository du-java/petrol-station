package by.du.petrolstation.facade;

import by.du.petrolstation.dto.DispenserDto;
import by.du.petrolstation.dto.PetrolDto;
import by.du.petrolstation.dto.TankDto;
import by.du.petrolstation.model.Station;
import by.du.petrolstation.service.DispenserService;
import by.du.petrolstation.service.PetrolService;
import by.du.petrolstation.service.StationService;
import by.du.petrolstation.service.TankService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RootFacade {
    private final PetrolService petrolService;
    private final DispenserService dispenserService;
    private final TankService tankService;
    private final StationService stationService;

    public List<PetrolDto> findAllPetrol(){
        return petrolService.findAll();
    }
    public List<DispenserDto> findAllDispenser(){
        return dispenserService.findAll();
    }
    public List<TankDto> findAllTank(){
        return tankService.findAll();
    }
    public Station find(){
        return stationService.find();
    }
}
