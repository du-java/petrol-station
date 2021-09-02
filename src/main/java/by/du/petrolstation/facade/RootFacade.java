package by.du.petrolstation.facade;

import by.du.petrolstation.dto.TankDto;
import by.du.petrolstation.model.Dispenser;
import by.du.petrolstation.model.Petrol;
import by.du.petrolstation.model.Station;
import by.du.petrolstation.repository.PetrolRepository;
import by.du.petrolstation.service.DispenserService;
import by.du.petrolstation.service.StationService;
import by.du.petrolstation.service.TankService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RootFacade {
    private final PetrolRepository petrolRepository;
    private final DispenserService dispenserService;
    private final TankService tankService;
    private final StationService stationService;

    public List<Petrol> findAllPetrol(){
        return petrolRepository.findAll();
    }
    public List<Dispenser> findAllDispenser(){
        return dispenserService.findAll();
    }
    public List<TankDto> findAllTank(){
        return tankService.findAll();
    }
    public Station find(){
        return stationService.find();
    }
}
