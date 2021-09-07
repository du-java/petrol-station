package by.du.petrolstation.service;

import by.du.petrolstation.dto.DispenserDto;
import by.du.petrolstation.model.Dispenser;
import by.du.petrolstation.model.Petrol;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DispenserDtoDispenserConverter implements Converter<DispenserDto, Dispenser> {

    private final PetrolService petrolService;

    @NonNull
    @Override
    public Dispenser convert(DispenserDto dto) {
        return Dispenser.builder()
                .id(dto.getId())
                .petrolList(dto.getPetrols().stream().map(this::getPetrol).collect(Collectors.toList()))
                .build();
    }

    private Petrol getPetrol(String petrolName) {
        return petrolService.findByName(petrolName);
    }
}
