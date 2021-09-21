package by.du.petrolstation.service.converter;

import by.du.petrolstation.dto.DispenserDto;
import by.du.petrolstation.model.Dispenser;
import by.du.petrolstation.model.Petrol;
import by.du.petrolstation.service.PetrolService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DispenserConverter implements Converter<DispenserDto, Dispenser> {

    private final PetrolService petrolService;

    @Override
    public Dispenser convert(DispenserDto dto) {
        return Dispenser.builder()
                .id(dto.getId())
                .petrolList(dto.getPetrols().stream().map(petrolService::findByName).collect(Collectors.toList()))
                .build();
    }

    @Override
    public DispenserDto convert(Dispenser model) {
        return DispenserDto.builder()
                .id(model.getId())
                .petrols(model.getPetrolList().stream().map(Petrol::getName).collect(Collectors.toList()))
                .build();
    }
}
