package by.du.petrolstation.service.converter;

import by.du.petrolstation.dto.StationDto;
import by.du.petrolstation.model.Dispenser;
import by.du.petrolstation.model.Station;
import by.du.petrolstation.model.Tank;
import by.du.petrolstation.service.DispenserService;
import by.du.petrolstation.service.TankService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class StationConverter implements Converter<StationDto, Station> {

    private final DispenserService dispenserService;
    private final TankService tankService;

    @Override
    public Station convert(StationDto dto) {
        return Station.builder()
                .id(dto.getId())
                .dispensers(dto.getDispensers().stream().map(dispenserService::findById).collect(Collectors.toList()))
                .tanks(dto.getDispensers().stream().map(tankService::findById).collect(Collectors.toList()))
                .build();
    }

    @Override
    public StationDto convert(Station model) {
        return StationDto.builder()
                .id(model.getId())
                .dispensers(model.getDispensers().stream().map(Dispenser::getId).collect(Collectors.toList()))
                .tanks(model.getTanks().stream().map(Tank::getId).collect(Collectors.toList()))
                .build();
    }
}
